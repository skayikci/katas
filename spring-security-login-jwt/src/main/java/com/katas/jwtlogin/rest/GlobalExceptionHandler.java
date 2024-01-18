package com.katas.jwtlogin.rest;

import java.util.Optional;

import com.katas.jwtlogin.exception.ExceptionMessagesEnum;
import com.katas.jwtlogin.exception.IncorrectPasswordException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<String> handleInvalidRequest(HttpServletRequest request) {
        HttpStatus status = HttpStatus.valueOf(
                (Integer) Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).orElse(400));

        return ResponseEntity.status(status).body(ExceptionMessagesEnum.EMPTY_REQUEST_BODY_EXCEPTION.getValue());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<String> handleUserNameNotFound(HttpServletRequest request) {
        HttpStatus status = HttpStatus.valueOf(
                (Integer) Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).orElse(400));

        return ResponseEntity.status(status).body(ExceptionMessagesEnum.WRONG_CREDENTIALS_EXCEPTION.getValue());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    ResponseEntity<String> handleIncorrectPassword(HttpServletRequest request) {
        HttpStatus status = HttpStatus.valueOf(
                (Integer) Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).orElse(400));

        return ResponseEntity.status(status).body(ExceptionMessagesEnum.INCORRECT_PASSWORD_EXCEPTION.getValue());
    }
}

