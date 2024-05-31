package com.sparta.spartanbcampscheduleplanpp2.entity;
import com.sparta.spartanbcampscheduleplanpp2.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String nickname;

    @Column(nullable = false, unique = false, length = 20)
    private String username;

    @Column(nullable = false, unique = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email; //이메일을 로그인 아이디로 사용하는게 좋을 것 같다.

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(SignupRequestDto requestDto, UserRoleEnum role) {
        this.nickname = requestDto.getNickname();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.email = requestDto.getEmail();
        this.role = role;
    }

    public void update(SignupRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.email = requestDto.getEmail();
    }
}
