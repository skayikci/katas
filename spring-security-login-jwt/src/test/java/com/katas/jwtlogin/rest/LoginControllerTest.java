package com.katas.jwtlogin.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katas.jwtlogin.exception.ExceptionMessagesEnum;
import com.katas.jwtlogin.model.LoginCredentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;


    // should access login without any authentication
    @Test
    void shouldAccessWithoutAnyAuthentication() throws Exception {
        var loginCredentials = new LoginCredentials("username", "password");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginCredentials))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    // should return a meaningful error when credentials aren't sent
    @Test
    void shouldReturnAMeaningfulErrorWhenCredentialsNotSent() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$").value(ExceptionMessagesEnum.EMPTY_REQUEST_BODY_EXCEPTION.getValue()))
                .andExpect(status().isBadRequest());
    }

    // should return a meaningful error when the user is not found
    @Test
    void shouldReturnAMeaningfulErrorWhenUserIsNotFound() throws Exception {
        var loginCredentials = new LoginCredentials("non-username", "password");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginCredentials))
                )
                .andDo(print())
                .andExpect(jsonPath("$").value(ExceptionMessagesEnum.WRONG_CREDENTIALS_EXCEPTION.getValue()))
                .andExpect(status().isBadRequest());
    }

    // should return a meaningful error when the password isn't correct
    // should return an object with credentials like authToken, refreshToken, validUntil, role when a valid input is given

}
