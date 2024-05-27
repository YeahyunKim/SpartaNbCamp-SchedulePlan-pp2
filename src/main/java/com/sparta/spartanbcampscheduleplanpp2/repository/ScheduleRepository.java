package com.sparta.spartanbcampscheduleplanpp2.repository;

import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
