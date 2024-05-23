package com.xoxo.backend.backendspringboot.models.dto;

import com.xoxo.backend.backendspringboot.models.entities.Pedido;
import com.xoxo.backend.backendspringboot.models.entities.Review;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
public class UsuarioResponseDto {
    private Long idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String correoUsuario;
    private Date fechaRegistro;
    private String contrasenaUsuario;
    private List<Review> reviewsUsuario;
    private List<Pedido> pedidosUsuario;
}
