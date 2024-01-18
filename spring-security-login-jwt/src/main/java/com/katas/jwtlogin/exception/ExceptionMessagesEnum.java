package com.katas.jwtlogin.exception;

public enum ExceptionMessagesEnum {

    INVALID_INPUTS("The login credentials you have entered aren't correct, please check again.");

    private final String value;

    ExceptionMessagesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
