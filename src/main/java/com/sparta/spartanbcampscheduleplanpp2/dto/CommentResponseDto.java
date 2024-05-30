package com.sparta.spartanbcampscheduleplanpp2.dto;

import com.sparta.spartanbcampscheduleplanpp2.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private LocalDateTime createdAt; // 생성일
    private LocalDateTime updatedAt; // 수정일


    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUsername();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
}
