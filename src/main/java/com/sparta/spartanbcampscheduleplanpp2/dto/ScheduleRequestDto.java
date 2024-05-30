package com.sparta.spartanbcampscheduleplanpp2.dto;

import com.sparta.spartanbcampscheduleplanpp2.repository.ScheduleRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class ScheduleRequestDto {
    @NotBlank(message = "제목은 입력해 주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;
    @NotBlank(message = "담당자 이름을 입력해 주세요.")
    private String managername;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

}