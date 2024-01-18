package com.katas.jwtlogin.rest;

import com.katas.jwtlogin.model.LoginCredentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void loginWithCredentials(LoginCredentials loginCredentials) {

    }
}
