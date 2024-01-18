package com.katas.jwtlogin.rest;

import com.katas.jwtlogin.model.LoginCredentials;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.katas.jwtlogin.exception.ExceptionMessagesEnum.WRONG_CREDENTIALS_EXCEPTION;

@RestController
@AllArgsConstructor
public class LoginController {

    private final InMemoryUserDetailsManager userCredentialsService;

    @PostMapping("/login")
    public void loginWithCredentials(@Valid @RequestBody LoginCredentials loginCredentials) {
        if (!userCredentialsService.userExists(loginCredentials.username())) {
            throw new UsernameNotFoundException(WRONG_CREDENTIALS_EXCEPTION.getValue());
        }
    }
}
