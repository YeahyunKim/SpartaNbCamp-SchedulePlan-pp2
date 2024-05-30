package com.sparta.spartanbcampscheduleplanpp2.service;

import com.sparta.spartanbcampscheduleplanpp2.dto.CommentReqeustDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.CommentResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.ScheduleRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.Comment;
import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;
import com.sparta.spartanbcampscheduleplanpp2.repository.CommentRepository;
import com.sparta.spartanbcampscheduleplanpp2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDto createComment(CommentReqeustDto reqeustDto) {
        Schedule scheduleById = scheduleRepository.findById(reqeustDto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 존재하지 않습니다. id=" + reqeustDto.getScheduleId()));

        Comment comment = new Comment(reqeustDto);

        comment.setSchedule(scheduleById);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
