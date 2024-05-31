package com.sparta.spartanbcampscheduleplanpp2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}