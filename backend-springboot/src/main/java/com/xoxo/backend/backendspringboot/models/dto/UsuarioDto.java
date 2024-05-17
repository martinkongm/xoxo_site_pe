package com.xoxo.backend.backendspringboot.models.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@Builder
public class UsuarioDto {

    private Integer idUsuario;

    private String nombre;

    private String apellido;

    private String correo;

    private Date fechaRegistro;

}
