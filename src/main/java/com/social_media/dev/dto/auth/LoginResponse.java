package com.social_media.dev.dto.auth;


import com.social_media.dev.dto.UserDetailsResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class LoginResponse {

    @NotNull
    private UserDetailsResponse user;

    @NotNull
    private String token;
}