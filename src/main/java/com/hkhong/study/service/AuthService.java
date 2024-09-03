package com.hkhong.study.service;

import com.hkhong.study.dto.UserDto;
import com.hkhong.study.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static String userName = "hkhong";
    private static String password = "hkhong2024!";
    private final JwtUtil jwtUtil;

    public String authenticateUser(UserDto userDto){
        if(userName.equals(userDto.getUserName()) && password.equals(userDto.getPassword())){
            return getToken(userDto.getUserName());
        }
        throw new RuntimeException("Invalid user");
    }

    public String getToken(String username){
        return jwtUtil.generateToken(username);
    }
}
