package com.hkhong.study.global.dto;

import com.hkhong.study.global.exception.CustomException;
import lombok.Builder;
import lombok.Getter;

public class CommonDto {

    @Getter
    @Builder
    public static class ErrorResponse {
        private String errorCode;
        private String errorMessage;

        public static ErrorResponse from(CustomException exception) {
            return ErrorResponse.builder()
                    .errorCode(exception.getErrorCode())
                    .errorMessage(exception.getErrorMessage())
                    .build();
        }
    }
}
