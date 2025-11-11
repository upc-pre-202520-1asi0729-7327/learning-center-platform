package com.acme.center.platform.shared.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleException(MethodArgumentNotValidException ex) {
        String message = ex.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage() == null ? "" : fieldError.getDefaultMessage()).reduce("", String::concat);
        return ErrorResponse.create(
                ex,
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),
                message
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleException(IllegalArgumentException ex) {
        return ErrorResponse.create(ex, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
    }

}
