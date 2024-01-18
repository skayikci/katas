package com.katas.jwtlogin.model;

import jakarta.validation.constraints.NotNull;

public record LoginCredentials(@NotNull String username,@NotNull String password) {
}
