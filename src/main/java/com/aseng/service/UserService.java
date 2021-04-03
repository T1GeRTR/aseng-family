package com.aseng.service;

import com.aseng.entity.LaborRecord;
import com.aseng.entity.Role;
import com.aseng.entity.User;
import com.aseng.exception.ServerException;
import com.aseng.repository.HoursRepository;
import com.aseng.repository.LaborRecordRepository;
import com.aseng.repository.UserRepository;
import com.aseng.request.UserDtoRequest;
import com.aseng.request.UserHoursDtoRequest;
import com.aseng.request.UsersHoursDtoRequest;
import com.aseng.response.UserDtoResponse;
import com.aseng.response.UserHoursDtoResponse;
import com.aseng.response.planfixResponse.PlanfixUser;
import com.aseng.utils.LaborRecordConverter;
import com.aseng.utils.MonthYearConverter;
import com.aseng.utils.Planfix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    HoursRepository hoursRepository;

    @Autowired
    LaborRecordRepository laborRecordRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDtoResponse(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.isCreated(), user.getRole());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public List<UserDtoResponse> update() throws ServerException {
        LOGGER.debug("Users UPDATE");
        Planfix planfix = new Planfix();
        List<User> usersFromPf = new ArrayList<>();
        List<PlanfixUser> usersFromPlanfix = planfix.getUsers();
        LOGGER.debug("Users in Planfix: {}", usersFromPlanfix.size());
        for (PlanfixUser user : usersFromPlanfix) {
            usersFromPf.add(new User(Long.valueOf(user.getId()), user.getEmail(), bCryptPasswordEncoder.encode("Xibs95r8#"), user.getFirstname(), user.getLastname(), user.getEmail(), Role.EMPLOYEE));
        }
        List<User> users = userRepository.findAll();
        List<User> usersUpdate = new ArrayList<>();
        List<User> userDelete = new ArrayList<>(users);
        List<User> usersNotAdd = new ArrayList<>();
        for (int i = 0; i < usersFromPf.size(); i++) {
            if (users.size() == 0) {
                break;
            }
            for (int j = 0; j < users.size(); j++) {
                if (usersFromPf.get(i).getId().equals(users.get(j).getId())) {
                    if (!usersFromPf.get(i).equals(users.get(j))) {
                        usersUpdate.add(usersFromPf.get(i));
                    }
                    usersNotAdd.add(usersFromPf.get(i));
                    break;
                }
            }
        }
        usersFromPf.removeAll(usersNotAdd);
        userDelete.removeAll(usersNotAdd);
        userDelete.removeAll(usersUpdate);
        LOGGER.debug("Users add: {}", usersFromPf.size());
        userRepository.saveAll(usersFromPf);
        LOGGER.debug("Users update: {}", usersUpdate.size());
        userRepository.saveAll(usersUpdate);
        LOGGER.debug("Users delete: {}", userDelete.size());
        userRepository.deleteAll(userDelete);
        return getAll();
    }

    public List<UserDtoResponse> getAll() {
        List<UserDtoResponse> getAllDtoResponses = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            getAllDtoResponses.add(new UserDtoResponse(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.isCreated(), user.getRole()));
        }
        return getAllDtoResponses;
    }

    public List<UserHoursDtoResponse> getFromDate(String monthYear) throws ServerException {
        List<UserHoursDtoResponse> getFromDates = new ArrayList<>();
        int month = MonthYearConverter.getMonth(monthYear);
        int year = MonthYearConverter.getYear(monthYear);
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = (from.getMonthValue() < LocalDate.now().getMonthValue() && from.getYear() <= LocalDate.now().getYear()) || (from.getYear() < LocalDate.now().getYear()) ? from.withDayOfMonth(from.lengthOfMonth()) : LocalDate.now();
        for (User user : userRepository.findAll()) {
            if (user != null) {
                getFromDates.add(new UserHoursDtoResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), LaborRecordConverter.convertDtoHours(LaborRecordConverter.convertHours(hoursRepository.findByDateBetweenAndUser(from, to, user.getId()), from, user))));
            }
        }
        return getFromDates;
    }

    public UserDtoResponse findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return new UserDtoResponse();
        }
        return new UserDtoResponse(user.get().getId(), user.get().getUsername(), user.get().getPassword(), user.get().getFirstName(), user.get().getLastName(), user.get().getEmail(), user.get().isCreated(), user.get().getRole());

    }

    public boolean saveUser(UserDtoRequest user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setRole(Role.EMPLOYEE);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(new User(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole()));
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
