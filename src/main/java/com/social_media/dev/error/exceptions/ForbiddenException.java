package com.social_media.dev.error.exceptions;


import com.social_media.dev.util.localization.Tokens;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() { super(Tokens.E_ACCESS_DENIED);}
    public ForbiddenException(String message) { super(message);}
}
