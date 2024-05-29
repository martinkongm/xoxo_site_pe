package com.xoxo.backend.backendspringboot.presentation.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(
        @NotBlank String username,
        String password) {
}
