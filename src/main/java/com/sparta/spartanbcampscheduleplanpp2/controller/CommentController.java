package com.sparta.spartanbcampscheduleplanpp2.controller;

import com.sparta.spartanbcampscheduleplanpp2.dto.CommentReqeustDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.CommentResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;
import com.sparta.spartanbcampscheduleplanpp2.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // =====[Create]===== 스케줄 작성
    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentReqeustDto commentReqeustDto) {
        return commentService.createComment(commentReqeustDto);
    }
}
