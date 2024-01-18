package com.katas.jwtlogin.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
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
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("username", "password"))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    // should return a meaningful error when credentials aren't sent => won't be needed as from: https://github.com/spring-projects/spring-boot/issues/31165

    // should return a meaningful error when the user is not found
    @Test
    void shouldReturnAMeaningfulErrorWhenUserIsNotFound() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("non-username", "password"))
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    // should return a meaningful error when the password isn't correct
    @Test
    void shouldReturnAMeaningfulErrorWhenIncorrectPassword() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("username", "invalid-password"))
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    // should return an object with credentials like authToken when a valid input is given
    @Test
    void shouldReturnJWT_TokenWhenValidUsernamePassword() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("username", "password"))
                )
                .andDo(print())
                .andExpect(jsonPath("$.authToken").exists())
                .andExpect(status().isOk())
                .andReturn();
    }

}
