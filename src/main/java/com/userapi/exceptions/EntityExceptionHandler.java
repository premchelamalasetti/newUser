package com.userapi.exceptions;

import org.springframework.http.HttpStatus;

public class EntityExceptionHandler {

    private  String message;
    private  HttpStatus httpStatus;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public EntityExceptionHandler(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
