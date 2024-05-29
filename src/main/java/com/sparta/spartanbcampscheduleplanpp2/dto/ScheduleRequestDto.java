package com.sparta.spartanbcampscheduleplanpp2.dto;

import com.sparta.spartanbcampscheduleplanpp2.repository.ScheduleRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String content;
    private String password;
}