package com.hkhong.study.global.exception;

import com.hkhong.study.global.dto.CommonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonDto.ErrorResponse> handleCustomException(CustomException ex) {
        return new ResponseEntity<>(CommonDto.ErrorResponse.from(ex), ex.getHttpStatus());
    }
}
