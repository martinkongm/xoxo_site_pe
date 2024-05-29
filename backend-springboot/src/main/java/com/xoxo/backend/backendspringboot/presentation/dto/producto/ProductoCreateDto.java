package com.xoxo.backend.backendspringboot.presentation.dto.producto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCreateDto {
    private String nombreProducto;
    private Double precioProducto;
    private Integer tamanoProducto;
    private String beneficiosProducto;
    private int stockProducto;
    private String imagenProducto;
    private Long idColeccion;
}
