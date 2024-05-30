package com.sparta.spartanbcampscheduleplanpp2.repository;

import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByCreatedAtDesc(); // 작성일 기준 정렬
}
