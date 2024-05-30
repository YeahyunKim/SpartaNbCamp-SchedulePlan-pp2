package com.sparta.spartanbcampscheduleplanpp2.service;

import com.sparta.spartanbcampscheduleplanpp2.dto.CommentReqeustDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.CommentResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.ScheduleRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.Comment;
import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;
import com.sparta.spartanbcampscheduleplanpp2.repository.CommentRepository;
import com.sparta.spartanbcampscheduleplanpp2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDto createComment(CommentReqeustDto requestDto) {
        //코멘트 예외처리 : 선택한 일정의 scheduleId를 입력받지 않은 경우
        if (requestDto.getScheduleId() == null) {
            throw new IllegalArgumentException("해당 아이디가 존재하지 않습니다.");
        }

        //코멘트 예외처리 : 댓글을 입력하지 않은 경우 scheduleId를 입력받지 않은 경우
        if (!StringUtils.hasText(requestDto.getContent())) {
            throw new IllegalArgumentException("댓글을 입력해 주세요.");
        }

        Schedule scheduleById = getScheduleById(requestDto);

        Comment comment = new Comment(requestDto, scheduleById);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    //코멘트 예외처리 : 스케쥴 아이디가 존재하는지 확인 -> 존재하면 해당 스케쥴 정보 가져오기
    public Schedule getScheduleById(CommentReqeustDto requestDto) {
        return scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 존재하지 않습니다. id=" + requestDto.getScheduleId()));
    }
}
