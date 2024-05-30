package com.sparta.spartanbcampscheduleplanpp2.controller;

import com.sparta.spartanbcampscheduleplanpp2.dto.CommentReqeustDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.CommentResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;
import com.sparta.spartanbcampscheduleplanpp2.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // =====[Create]===== 댓글 작성
    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody @Valid CommentReqeustDto commentReqeustDto) {
        return commentService.createComment(commentReqeustDto);
    }

    // =====[Delete]===== 댓글 삭제
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
