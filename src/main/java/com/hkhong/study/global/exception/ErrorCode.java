package com.hkhong.study.global.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST("잘못된 요청입니다.", "1000", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", "1001", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("서버 오류가 발생했습니다.", "1002",HttpStatus.INTERNAL_SERVER_ERROR),

    TOKEN_EXPIRED("JWT 토큰이 만료되었습니다.", "9001",HttpStatus.UNAUTHORIZED),
    INVALID_SIGNATURE("잘못된 JWT 서명입니다.", "9002",HttpStatus.UNAUTHORIZED ),
    TOKEN_ERROR("JWT 토큰 검증 중 오류가 발생했습니다.", "9003",HttpStatus.UNAUTHORIZED),
    ;

    private final String message;
    private final String code;
    private final HttpStatus status;
}
