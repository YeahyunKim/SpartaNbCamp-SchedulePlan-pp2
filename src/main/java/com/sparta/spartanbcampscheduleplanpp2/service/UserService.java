package com.sparta.spartanbcampscheduleplanpp2.service;

import com.sparta.spartanbcampscheduleplanpp2.dto.LoginRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.SignupRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.SignupResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.User;
import com.sparta.spartanbcampscheduleplanpp2.entity.UserRoleEnum;
import com.sparta.spartanbcampscheduleplanpp2.jwtUtil.JwtUtil;
import com.sparta.spartanbcampscheduleplanpp2.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;



    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "7Iqk7YyM66W07YOA7L2U65Sp7YG065+9U3ByaW5n6rCV7J2Y7Yqc7YSw7LWc7JuQ67mI7J6F64uI64ukLg==";

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
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

        return new ResponseEntity<String>("회원가입에 성공했습니다!", HttpStatus.ACCEPTED);
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

    public ResponseEntity<String> loginUser(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if (!(password.equals(user.getPassword()))) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        jwtUtil.addJwtToCookie(token, res);

        return new ResponseEntity<String>("로그인에 성공했습니다!", HttpStatus.ACCEPTED);
    }


















//    public UserResponseDto checkIdExist(Long id) {
//        Optional <User> result = userRepository.findById(id);
//        User user = result.orElseThrow(() -> new IllegalArgumentException("해당 아이디는 존재하지 않습니다."));
//        return new UserResponseDto(user);
//    }
}
