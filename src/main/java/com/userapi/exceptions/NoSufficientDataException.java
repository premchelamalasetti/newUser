package com.userapi.exceptions;

public class NoSufficientDataException extends RuntimeException{
    public NoSufficientDataException(String message) {
        super(message);
    }
}
