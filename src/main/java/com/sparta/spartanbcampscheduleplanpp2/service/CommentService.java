package com.sparta.spartanbcampscheduleplanpp2.service;

import com.sparta.spartanbcampscheduleplanpp2.dto.CommentReqeustDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.CommentResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.Comment;
import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;
import com.sparta.spartanbcampscheduleplanpp2.repository.CommentRepository;
import com.sparta.spartanbcampscheduleplanpp2.repository.ScheduleRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    // =====[Create]===== 댓글 등록
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

    // =====[Update]===== 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(CommentReqeustDto commentReqeustDto, Long id) {
        //아이디 존재 여부 확인 + 선택된 코멘트 가져오기
        Comment commentById = getCommentById(id);

        //스케줄 아이디가 존재하지 않으면 예외처리
        getScheduleById(commentReqeustDto);

        //댓글 아이디를 입력하지 않았을 때 예외처리
        if (id == null) {
            throw new IllegalArgumentException("댓글 아이디를 입력해주세요.");
        }

        //댓글 등록자와 수정요청자의 이름이 같지 않을 때 예외처리
        if (!commentById.getUsername().equals(commentReqeustDto.getUsername())) {
            throw new IllegalArgumentException("수정권한이 없습니다.");
        }

        commentById.update(commentReqeustDto);

        return new CommentResponseDto(commentById);
    }

    // =====[Delete]===== 댓글 삭제
    public ResponseEntity<String> deleteComment(Long id) {
        //아이디 존재 여부 확인 + 선택된 코멘트 가져오기
        Comment commentById = getCommentById(id);

        commentRepository.delete(commentById);

        ResponseEntity<String> responseEntity = new ResponseEntity<String>("CommentId: " + commentById.getId() + " 가 삭제되었습니다.", HttpStatus.ACCEPTED);

        return responseEntity;
    }



    //코멘트 예외처리 : 스케쥴 아이디가 존재하는지 확인 -> 존재하면 해당 스케쥴 정보 가져오기
    public Schedule getScheduleById(CommentReqeustDto requestDto) {
        return scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 존재하지 않습니다. id=" + requestDto.getScheduleId()));
    }

    //코멘트 예외처리 : 댓글 아이디가 존재하는지 확인 -> 존재하면 해당 댓글 정보 가져오기
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
    }

}
