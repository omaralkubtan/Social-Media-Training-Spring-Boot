package com.social_media.dev.dto;

import com.social_media.dev.util.helper.Constants;
import com.social_media.dev.util.localization.Tokens;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegistrationDto {

    private String username;

    @Pattern(regexp = Constants.EmailRegex, message = Tokens.E_INVALID_EMAIL)
    private String email;

    private String password;
}