package com.xoxo.backend.backendspringboot.presentation.dto.usuario;

import com.xoxo.backend.backendspringboot.persistence.entity.Pedido;
import com.xoxo.backend.backendspringboot.persistence.entity.Review;
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
    private List<Review> reviewsUsuario;
    private List<Pedido> pedidosUsuario;
}
