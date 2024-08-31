package com.social_media.dev.error.exceptions;


import com.social_media.dev.util.localization.Tokens;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super(Tokens.E_BAD_REQUEST);
    }
    public BadRequestException(String message) {
        super(message);
    }
}
