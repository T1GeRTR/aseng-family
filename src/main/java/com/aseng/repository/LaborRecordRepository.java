package com.aseng.repository;

import com.aseng.entity.LaborRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LaborRecordRepository extends JpaRepository<LaborRecord, Long> {
    List<LaborRecord> findByDateBetween(LocalDate from, LocalDate to);

    List<LaborRecord> findByDateBetweenAndUser(LocalDate from, LocalDate to, Long userId);
}
