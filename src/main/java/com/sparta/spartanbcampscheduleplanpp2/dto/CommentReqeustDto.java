package com.sparta.spartanbcampscheduleplanpp2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CommentReqeustDto {
    //NotBlank 는 String만 검사를 해준다..
    //따라서 Long 타입인 ScheduleId 에서 NotBlank를 사용하면 에러가 난다..!
    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;
    @NotBlank(message = "유저 이름을 입력해 주세요.")
    private String username;
    @NotNull(message = "일정 Id를 입력해 주세요.")
    private Long scheduleId;
}
