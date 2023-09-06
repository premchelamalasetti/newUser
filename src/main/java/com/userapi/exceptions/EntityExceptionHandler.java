package com.userapi.exceptions;

import org.springframework.http.HttpStatus;

public class EntityExceptionHandler {

    private final String message;
    private final HttpStatus httpStatus;


    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public EntityExceptionHandler(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
