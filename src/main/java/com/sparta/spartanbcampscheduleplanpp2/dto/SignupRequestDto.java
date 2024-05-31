package com.sparta.spartanbcampscheduleplanpp2.dto;

import com.sparta.spartanbcampscheduleplanpp2.entity.UserRoleEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotBlank(message = "닉네임를 입력해 주세요.")
    @Size(min = 4, max = 10, message = "닉네임는 최소 4자 이상, 10자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "아이디는 알파벳 소문자(a~z)와 숫자(0~9)로만 구성되어야 합니다.")
    private String nickname;

    @NotBlank(message = "이름을 입력해 주세요.")
    @Size(min = 1, max = 20, message = "이름은 최소 1자 이상, 20자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "이름은 숫자와 특수문자를 제외한 한글과 영어만 가능합니다.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자 이상, 15자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "비밀번호는 알파벳 대소문자(a~z, A~Z)와 숫자(0~9)로만 구성되어야 합니다.")
    private String password;

    @Email(message = "올바른 이메일 형식을 입력해 주세요.")
    @NotEmpty(message = "이메일을 입력해 주세요.")
    private String email;

    @NotNull
    private boolean admin = false;

    private String adminToken = "";
}
