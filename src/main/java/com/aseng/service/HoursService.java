package com.aseng.service;

import com.aseng.entity.LaborRecord;
import com.aseng.entity.User;
import com.aseng.exception.ServerException;
import com.aseng.repository.HoursRepository;
import com.aseng.repository.LaborRecordRepository;
import com.aseng.repository.UserRepository;
import com.aseng.response.EmptyResponse;
import com.aseng.response.planfixResponse.PlanfixLaborRecord;
import com.aseng.utils.LaborRecordConverter;
import com.aseng.utils.Planfix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HoursService {

    @Autowired
    HoursRepository hoursRepository;

    @Autowired
    LaborRecordRepository laborRecordRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(HoursService.class);

    @Scheduled(cron = "0 0 1 * * *")
    public EmptyResponse update() throws ServerException {
        Planfix planfix = new Planfix();
        LocalDate now = LocalDate.now();
        LocalDate from;
        List<PlanfixLaborRecord> planfixLaborRecords;
        if (now.getDayOfMonth() <= 8) {
            from = LocalDate.of(now.getYear(), now.getMonthValue() - 1, 1);
        } else {
            from = now.withDayOfMonth(1);
        }
        planfixLaborRecords = planfix.getHours(from, now);
        LOGGER.debug("Hours in Planfix: {}", planfixLaborRecords.size());
        List<LaborRecord> laborRecords = laborRecordRepository.findByDateBetween(from, now);
        List<LaborRecord> laborsUpdate = new ArrayList<>();
        List<LaborRecord> laborsIdDelete = new ArrayList<>(laborRecords);
        List<LaborRecord> laborsNotAdd = new ArrayList<>();
        List<LaborRecord> laborRecordsFromPlanfix = LaborRecordConverter.fromPlanfix(planfixLaborRecords);
        List<Integer> deleteIds = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (LaborRecord laborRecord : laborRecordsFromPlanfix) {
            for (User user : users) {
                if (laborRecord.getUser().getFirstName().equals(user.getFirstName()) && laborRecord.getUser().getLastName().equals(user.getLastName())) {
                    laborRecord.setUser(user);
                    break;
                }
            }
        }
        for (int i = 0; i < laborRecordsFromPlanfix.size(); i++) {
            for (int j = 0; j < laborRecordsFromPlanfix.size(); j++) {
                LaborRecord labor1 = laborRecordsFromPlanfix.get(i);
                LaborRecord labor2 = laborRecordsFromPlanfix.get(j);
                if (labor1.getUser().getFirstName().equals(labor2.getUser().getFirstName()) && labor1.getUser().getLastName().equals(labor2.getUser().getLastName()) && labor1.getUser().getEmail().equals(labor2.getUser().getEmail()) && labor1.getDate().equals(labor2.getDate()) && labor1.getTaskId() == labor2.getTaskId() && labor1.getProjectId() == labor2.getProjectId())
                    if (i != j) {
                        if (!(deleteIds.contains(i) || deleteIds.contains(j))) {
                            labor1.setHours(labor1.getHours() + labor2.getHours());
                            deleteIds.add(j);
                        }
                    }
            }
        }
        for (int index : deleteIds) {
            laborRecordsFromPlanfix.set(index, null);
        }
        laborRecordsFromPlanfix.removeIf(Objects::isNull);
        for (int i = 0; i < laborRecordsFromPlanfix.size(); i++) {
            if (laborRecords.size() == 0) {
                break;
            }
            for (int j = 0; j < laborRecords.size(); j++) {
                boolean equals = laborRecordsFromPlanfix.get(i).getProjectId() == laborRecords.get(j).getProjectId() && laborRecordsFromPlanfix.get(i).getTaskId() == laborRecords.get(j).getTaskId() && laborRecordsFromPlanfix.get(i).getDate().equals(laborRecords.get(j).getDate()) && laborRecordsFromPlanfix.get(i).getUser().getFirstName().equals(laborRecords.get(j).getUser().getFirstName()) && laborRecordsFromPlanfix.get(i).getUser().getLastName().equals(laborRecords.get(j).getUser().getLastName()) && laborRecordsFromPlanfix.get(i).getUser().getEmail().equals(laborRecords.get(j).getUser().getEmail());
                if (equals) {
                    if (!(laborRecordsFromPlanfix.get(i).getHours() == laborRecords.get(j).getHours())) {
                        laborRecords.get(j).setHours(laborRecordsFromPlanfix.get(i).getHours());
                        laborsUpdate.add(laborRecords.get(j));
                    }
                    laborsNotAdd.add(laborRecordsFromPlanfix.get(i));
                    break;
                }
            }
        }
        laborRecordsFromPlanfix.removeAll(laborsNotAdd);
        laborsIdDelete.removeAll(laborsNotAdd);
        laborsIdDelete.removeAll(laborsUpdate);
        LOGGER.debug("UserHours add: {}", laborRecordsFromPlanfix.size());
        if (laborRecordsFromPlanfix.size() > 0) {
            laborRecordRepository.saveAll(laborRecordsFromPlanfix);
            hoursRepository.saveAll(LaborRecordConverter.convertToHours(laborRecordsFromPlanfix));
        }
        LOGGER.debug("UserHours update: {}", laborsUpdate.size());
        laborRecordRepository.saveAll(laborsUpdate);
        hoursRepository.saveAll(LaborRecordConverter.convertToHours(laborsUpdate));
        LOGGER.debug("UserHours delete: {}", laborsIdDelete.size());
        laborRecordRepository.deleteAll(laborsIdDelete);
        hoursRepository.deleteAll(LaborRecordConverter.convertToHours(laborsIdDelete));
        return new EmptyResponse();
    }
}
