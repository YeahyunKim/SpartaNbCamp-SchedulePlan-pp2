package com.sparta.spartanbcampscheduleplanpp2.dto;

import com.sparta.spartanbcampscheduleplanpp2.entity.User;
import com.sparta.spartanbcampscheduleplanpp2.entity.UserRoleEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignupResponseDto {
    Long id;                         // 고유 아이디
    String nickname;                 // 유저 닉네임
    String username;                 // 유저 이름
    String password;                 // 비밀번호
    String email;                    // 이메일
    UserRoleEnum role;
    private LocalDateTime createdAt; // 생성일
    private LocalDateTime updatedAt; // 수정일

    public SignupResponseDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
