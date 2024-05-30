package com.sparta.spartanbcampscheduleplanpp2.entity;

import com.sparta.spartanbcampscheduleplanpp2.dto.CommentReqeustDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment(CommentReqeustDto reqeustDto, Schedule schedule) {
        this.content = reqeustDto.getContent();
        this.username = reqeustDto.getUsername();
        this.schedule = schedule;
    }

    public void update(CommentReqeustDto commentReqeustDto) {
        this.content = commentReqeustDto.getContent();
    }
}
