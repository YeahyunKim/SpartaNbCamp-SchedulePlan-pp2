package com.sparta.spartanbcampscheduleplanpp2.dto;

import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;

import java.time.LocalDateTime;

public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String password;
    private LocalDateTime createdAt; // 생성일
    private LocalDateTime updatedAt; // 수정일

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.password = schedule.getPassword();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}



