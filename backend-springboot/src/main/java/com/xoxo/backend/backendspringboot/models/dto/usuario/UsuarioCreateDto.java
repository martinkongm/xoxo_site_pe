package com.xoxo.backend.backendspringboot.models.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDto {
    private String nombreUsuario;
    private String apellidoUsuario;
    private String correoUsuario;
    private Date fechaRegistro;
    private String contrasenaUsuario;
}