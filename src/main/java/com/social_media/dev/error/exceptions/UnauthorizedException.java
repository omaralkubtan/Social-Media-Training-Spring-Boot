package com.social_media.dev.error.exceptions;

import com.social_media.dev.util.localization.Tokens;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException() { super(Tokens.E_UNAUTHORIZED);}

    public UnauthorizedException(String message) { super(message);}
}
