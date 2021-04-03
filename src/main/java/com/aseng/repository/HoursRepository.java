package com.aseng.repository;

import com.aseng.entity.Hours;
import com.aseng.entity.LaborRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HoursRepository extends JpaRepository<Hours, Long> {
    List<Hours> findByDateBetweenAndUser(LocalDate from, LocalDate to, Long userId);
}
