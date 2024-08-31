package com.social_media.dev.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginRequest {

    @NotBlank(message = "Please enter your username")
    private String username;

    @NotBlank(message = "Please enter your password")
    private String password;
}