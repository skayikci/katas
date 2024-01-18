package com.katas.jwtlogin.rest;

import java.util.Optional;

import com.katas.jwtlogin.exception.ExceptionMessagesEnum;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidRequest(HttpServletRequest request) {
        HttpStatus status = HttpStatus.valueOf(
                (Integer) Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).orElse(400));

        return ResponseEntity.status(status).body(ExceptionMessagesEnum.INVALID_INPUTS.getValue());
    }
}

