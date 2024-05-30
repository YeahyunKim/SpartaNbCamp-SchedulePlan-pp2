package com.sparta.spartanbcampscheduleplanpp2.entity;

import com.sparta.spartanbcampscheduleplanpp2.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String content;

    @Column(nullable = false, length = 10)
    private String managername;

    @Column(nullable = false, length = 50)
    private String password;

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
        this.managername = requestDto.getManagername();
    }
    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.managername = requestDto.getManagername();
        //일정 수정이기 때문에, password 변경은 제외
    }
}
