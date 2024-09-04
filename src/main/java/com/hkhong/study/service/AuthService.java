package com.hkhong.study.service;

import com.hkhong.study.dto.UserDto;
import com.hkhong.study.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private static String userName = "hkhong";
    private static String password = "hkhong2024!";
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("hkhong".equals(username)) {
            return User
                    .withUsername("hkhong")
                    .password(passwordEncoder.encode("password"))
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public String getToken(String username){
        return jwtUtil.generateToken(username);
    }
}
