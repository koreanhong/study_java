package com.hkhong.study.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public CustomException(ErrorCode errorCode) {
        this.httpStatus = errorCode.getStatus();
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }
}
