package com.katas.jwtlogin.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String value) {
        super(value);
    }
}
