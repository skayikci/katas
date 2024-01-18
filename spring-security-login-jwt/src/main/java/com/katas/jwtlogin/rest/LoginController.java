package com.katas.jwtlogin.rest;

import com.katas.jwtlogin.exception.IncorrectPasswordException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.katas.jwtlogin.exception.ExceptionMessagesEnum.INCORRECT_PASSWORD_EXCEPTION;
import static com.katas.jwtlogin.exception.ExceptionMessagesEnum.WRONG_CREDENTIALS_EXCEPTION;

@RestController
@AllArgsConstructor
public class LoginController {

    private final InMemoryUserDetailsManager userCredentialsService;

    private final TokenService tokenService;

    @PostMapping("/login")
    public String loginWithCredentials(Authentication loginCredentials) {
        if (!userCredentialsService.userExists(loginCredentials.getName())) {
            throw new UsernameNotFoundException(WRONG_CREDENTIALS_EXCEPTION.getValue());
        }
        if (!loginCredentials.isAuthenticated()) {
            throw new IncorrectPasswordException(INCORRECT_PASSWORD_EXCEPTION.getValue());
        }

        var token = tokenService.generateToken(loginCredentials);
        return """
                {
                    "authToken": "%s"
                }
                """.formatted(token);
    }
}
