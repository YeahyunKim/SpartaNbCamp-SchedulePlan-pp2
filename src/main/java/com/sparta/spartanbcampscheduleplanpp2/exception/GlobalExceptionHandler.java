package com.sparta.spartanbcampscheduleplanpp2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j(topic = "Validation 에러메세지 츨력")
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> exampleResponseValidation(MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();
        e.getAllErrors().forEach(
                c -> {
                    error.put(((FieldError) c).getField(), c.getDefaultMessage());
                    log.error(c.getDefaultMessage());
                }
        );
        return ResponseEntity.badRequest().body(error);
    }
}
