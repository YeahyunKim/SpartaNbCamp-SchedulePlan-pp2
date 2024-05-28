package com.sparta.spartanbcampscheduleplanpp2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class UserRequestDto {
    String username;
    String password;
    String email;
}
