package com.hkhong.study.controller;

import com.hkhong.study.global.exception.CustomException;
import com.hkhong.study.global.exception.ErrorCode;
import com.hkhong.study.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final JwtUtil jwtUtil;

    @GetMapping("")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token){
        // "Bearer " 문자열을 제거
        token = token.substring(7);

        //강제에러 발생!!!!!!!!!!!!!!!!!!!!!!
        if(true) throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);

        // 유저정보 리턴
        return ResponseEntity.ok(jwtUtil.extractClaims(token));
    }
}
