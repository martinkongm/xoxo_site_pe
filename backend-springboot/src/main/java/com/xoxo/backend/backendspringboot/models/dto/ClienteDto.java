package com.xoxo.backend.backendspringboot.models.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ClienteDto implements Serializable {

    private Integer idCliente;

    private String nombre;

    private String apellido;

    private String correo;

    private Date fechaRegistro;

}
