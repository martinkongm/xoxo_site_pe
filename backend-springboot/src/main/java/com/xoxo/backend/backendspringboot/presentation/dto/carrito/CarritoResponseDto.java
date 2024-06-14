package com.xoxo.backend.backendspringboot.presentation.dto.carrito;

import com.xoxo.backend.backendspringboot.persistence.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarritoReponseDto {
    private Long id;
    private String correoUsuario;
    private int cantidad;
    private double precioTotal;
    private List<Producto> productos;
}
