package com.katas.jwtlogin.exception;

public enum ExceptionMessagesEnum {

    EMPTY_REQUEST_BODY_EXCEPTION("You didn't send any username or password, please check again."),
    WRONG_CREDENTIALS_EXCEPTION("The login credentials you have entered aren't correct, please check again."),
    INCORRECT_PASSWORD_EXCEPTION("The login credentials you have entered aren't correct, please check again.");


    private final String value;

    ExceptionMessagesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
