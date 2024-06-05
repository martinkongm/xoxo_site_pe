package com.xoxo.backend.backendspringboot.presentation.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(
        @NotBlank String email,
        String password) {
}
