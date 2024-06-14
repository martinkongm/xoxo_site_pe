package com.xoxo.backend.backendspringboot.presentation.dto.carrito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleResponseDto {
    private Long id;
    private int cantidad;
    private double precioTotal;
    private String nombreProducto;
}
