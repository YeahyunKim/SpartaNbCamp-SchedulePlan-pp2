package com.sparta.spartanbcampscheduleplanpp2.controller;

import com.sparta.spartanbcampscheduleplanpp2.dto.SignupRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.SignupResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //유저 조회 로직
    @GetMapping("/user")
    public SignupResponseDto findUser(@RequestParam Long id) {
        return userService.findUser(id);
    }

    //전체 유저 조회
    @GetMapping("/users")
    public List<SignupResponseDto> findAllUsers() {
        return userService.findAllUser();
    }

    //유저 정보 수정
    @PutMapping("/user/{id}")
    public SignupResponseDto editUser(@RequestBody @Valid SignupRequestDto requestDto, @PathVariable Long id) {
        return userService.editUser(id, requestDto);
    }

    @PostMapping("/user")
    public ResponseEntity<String> signupUser(@RequestBody @Valid SignupRequestDto requestDto) {
        return userService.signupUser(requestDto);
    }

    @DeleteMapping("/user/{id}")
    public Long deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
