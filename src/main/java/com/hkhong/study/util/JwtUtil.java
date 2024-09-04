package com.hkhong.study.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private String secretKey = "hkhong"; // 비밀 키 설정
    private long expiration = 1000 * 60 * 60; //토큰 유효시간(1시간)

    private Map<String, Object> getUserInfo(){
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", "korean hong");
        userInfo.put("age", 31);
        userInfo.put("sex", "M");
        userInfo.put("hasGirlfriend", "Y");
        return userInfo;
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setClaims(getUserInfo())
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //토큰 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();// 토큰 생성
    }

    // 토큰에서 사용자 이름 추출
    public String extractUsername(String token) {
        return extractClaims(token).getSubject(); // 클레임에서 사용자 이름 추출
    }

    //클레임 추출
    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJwt(token)
                .getBody();
    }

    //토큰 만료 여부
    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    //토큰 유효성 검증
    public boolean validateToken(String token, String username){
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

}
