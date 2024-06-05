package com.xoxo.backend.backendspringboot.presentation.dto.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String correo,
        @NotBlank String password,
        @Valid AuthCreateRoleRequest roleRequest) {
}
