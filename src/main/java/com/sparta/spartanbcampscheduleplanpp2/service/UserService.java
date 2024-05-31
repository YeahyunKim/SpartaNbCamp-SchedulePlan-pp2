package com.sparta.spartanbcampscheduleplanpp2.service;

import com.sparta.spartanbcampscheduleplanpp2.dto.SignupRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.SignupResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.User;
import com.sparta.spartanbcampscheduleplanpp2.entity.UserRoleEnum;
import com.sparta.spartanbcampscheduleplanpp2.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "7Iqk7YyM66W07YOA7L2U65Sp7YG065+9U3ByaW5n6rCV7J2Y7Yqc7YSw7LWc7JuQ67mI7J6F64uI64ukLg==";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> signupUser(SignupRequestDto requestDto) {
        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(requestDto, role);

        userRepository.save(user);
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("회원가입에 성공했습니다!", HttpStatus.ACCEPTED);
        return responseEntity;
    }

    public SignupResponseDto findUser(Long id) {
        return new SignupResponseDto(getUserById(id));
    }

    public List<SignupResponseDto> findAllUser() {
        return userRepository.findAllByOrderByCreatedAtDesc().stream().map(SignupResponseDto::new).toList();
    }

    @Transactional
    public SignupResponseDto editUser(Long id, SignupRequestDto requestDto) {
        User userById = getUserById(id);
        userById.update(requestDto);
        return new SignupResponseDto(userById);
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


//    public UserResponseDto checkIdExist(Long id) {
//        Optional <User> result = userRepository.findById(id);
//        User user = result.orElseThrow(() -> new IllegalArgumentException("해당 아이디는 존재하지 않습니다."));
//        return new UserResponseDto(user);
//    }
}
