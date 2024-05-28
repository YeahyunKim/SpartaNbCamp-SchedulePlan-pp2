package com.sparta.spartanbcampscheduleplanpp2.dto;

import com.sparta.spartanbcampscheduleplanpp2.entity.User;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    Long id;                         // 고유 아이디
    String username;                 // 유저 이름
    String password;                 // 비밀번호
    String email;                    // 이메일
    private LocalDateTime createdAt; // 생성일
    private LocalDateTime updatedAt; // 수정일

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
