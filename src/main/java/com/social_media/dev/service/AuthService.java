package com.social_media.dev.service;

import com.social_media.dev.dto.auth.LoginRequest;
import com.social_media.dev.entity.User;
import com.social_media.dev.repository.UserRepository;
import com.social_media.dev.util.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    public String login(String username, String password) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        var user = (User) auth.getPrincipal();

        return jwtTokenUtil.generateAccessToken(user);
    }

}
