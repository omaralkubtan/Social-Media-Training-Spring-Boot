package com.social_media.dev.error;

import com.social_media.dev.error.exceptions.BadRequestException;
import com.social_media.dev.error.exceptions.ForbiddenException;
import com.social_media.dev.error.exceptions.UnauthorizedException;
import com.social_media.dev.util.localization.Tokens;
import com.social_media.dev.util.localization.Translator;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.*;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private Translator translator;


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        var message = translator.t(ex.getConstraintName());

        var subErrors = new ArrayList<ApiSubError>();

        var apiError = new ApiError(BAD_REQUEST, message, subErrors);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        var message = translator.t(ex.getMessage());
        var apiError = new ApiError(NOT_FOUND, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExists(EntityExistsException ex) {
        var message = translator.t(ex.getMessage());
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
        var message = translator.t(Tokens.E_BAD_CREDS);
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        var message = translator.t(Tokens.E_ACCESS_DENIED);
        var apiError = new ApiError(FORBIDDEN, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(BadRequestException ex) {
        var message = translator.t(ex.getMessage());
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<Object> handleForbidden(ForbiddenException ex) {
        var message = translator.t(ex.getMessage());
        var apiError = new ApiError(FORBIDDEN, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    protected ResponseEntity<Object> handleSizeLimitExceeded(SizeLimitExceededException ex) {
        var message = translator.t(Tokens.E_SIZE_LIMIT_EXCEEDED);
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleSizeLimitExceeded(UnauthorizedException ex) {
        var message = translator.t(Tokens.E_SIZE_LIMIT_EXCEEDED);
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
