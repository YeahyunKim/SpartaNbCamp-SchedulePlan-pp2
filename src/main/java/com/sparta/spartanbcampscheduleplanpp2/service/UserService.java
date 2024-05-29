package com.sparta.spartanbcampscheduleplanpp2.service;

import com.sparta.spartanbcampscheduleplanpp2.dto.UserRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.UserResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.User;
import com.sparta.spartanbcampscheduleplanpp2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto signupUser(UserRequestDto requestDto) {
        User user = new User(requestDto);
        System.out.println("test: " + user.getUsername() + " " + user.getPassword());
        User save = userRepository.save(user);
        System.out.println("test2: " + user.getId() + " " + save.getId());
        UserResponseDto responseDto = new UserResponseDto(save);
        return responseDto;
    }

    public UserResponseDto findUser(Long id) {
        return new UserResponseDto(getUserById(id));
    }

    public List<UserResponseDto> findAllUser() {
        return userRepository.findAllByOrderByCreatedAtDesc().stream().map(UserResponseDto::new).toList();
    }

    @Transactional
    public UserResponseDto editUser(Long id, UserRequestDto requestDto) {
        User userById = getUserById(id);
        userById.update(requestDto);
        return new UserResponseDto(userById);
    }

    public Long deleteUser(Long id) {
        User userById = getUserById(id);
        userRepository.delete(userById);
        return id;
    }

    //유저 찾기
    private User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디는 존재하지 않습니다."));
        return user;
    }

}
