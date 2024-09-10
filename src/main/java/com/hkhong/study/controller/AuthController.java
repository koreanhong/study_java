package com.hkhong.study.controller;

import com.hkhong.study.dto.UserDto;
import com.hkhong.study.service.AuthService;
import com.hkhong.study.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {

            // 1. 로그인 요청 (username과 password)
            String username = userDto.getId();
            String password = userDto.getPassword();

            // 2. UsernamePasswordAuthenticationToken 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getId(), userDto.getPassword());

            // 3. AuthenticationManager 호출
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // 4. UserDetailsService 호출 하여 UserDetailsService가 사용자의 UserDetails 정보 호출
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getId());

            // 5. UserDetails 확인 하여 UserDetails의 정보와 입력한 정보가 일치하는지 확인
            if (userDetails != null && passwordEncoder.matches(userDto.getPassword(), userDetails.getPassword())){
                // 인증 성공
                // 6. SecurityContext 객체 저장
                // SecurityContextHolder에 인증 후 객체를 저장해 SecurityContext에 사용자 정보 보관
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // JWT 토큰 생성 및 반환
                String jwtToken = jwtUtil.generateToken(userDto.getId());
                return ResponseEntity.ok(jwtToken);
            }else {
                throw new BadCredentialsException("Invalid User");
            }

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid Authentication Information");
        }
    }
}
