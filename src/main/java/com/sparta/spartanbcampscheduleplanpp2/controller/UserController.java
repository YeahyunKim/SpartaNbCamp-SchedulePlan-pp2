package com.sparta.spartanbcampscheduleplanpp2.controller;

import com.sparta.spartanbcampscheduleplanpp2.dto.UserRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.UserResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.User;
import com.sparta.spartanbcampscheduleplanpp2.repository.ScheduleRepository;
import com.sparta.spartanbcampscheduleplanpp2.repository.UserRepository;
import com.sparta.spartanbcampscheduleplanpp2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    public UserResponseDto findUser(@RequestParam Long id) {
        return userService.findUser(id);
    }

    //전체 유저 조회
    @GetMapping("/users")
    public List<UserResponseDto> findAllUsers() {
        return userService.findAllUser();
    }

    //유저 정보 수정
    @PutMapping("/user/{id}")
    public UserResponseDto editUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.editUser(id, requestDto);
    }

    //유저 정보 등록(회원가입)
    @PostMapping("/user")
    public UserResponseDto signupUser(@RequestBody UserRequestDto requestDto) {
        return userService.signupUser(requestDto);
    }

    //유저 정보 삭제
    @DeleteMapping("/user/{id}")
    public Long deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }


}
