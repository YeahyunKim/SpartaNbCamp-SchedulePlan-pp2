package com.sparta.spartanbcampscheduleplanpp2.controller;

import com.sparta.spartanbcampscheduleplanpp2.dto.ScheduleRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.ScheduleResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;
import com.sparta.spartanbcampscheduleplanpp2.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // =====[Create]===== 1단계 일정 작성
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createMemo(requestDto);
    }

    // =====[Read]===== 2단계 선택한 일정 조회
    @GetMapping("/schedule")
    public Schedule findSchedule(@RequestParam Long id) {
        return scheduleService.findSchedule(id);
    }

    // =====[Read]===== 3단계 전체 일정 조회
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleService.getSchedules();
    }

    // =====[Update]===== 4단계 선택한 일정 수정
    @PutMapping("/schedule/{id}/{password}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @PathVariable String password, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto, password);
    }

    // =====[Delete]===== 5단계 선택한 일정 삭제
    @DeleteMapping("/schedule/{id}/{password}")
    public Long deleteSchedule(@PathVariable Long id, @PathVariable String password) {
        return scheduleService.deleteSchedule(id, password);
    }
}