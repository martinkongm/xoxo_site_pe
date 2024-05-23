package com.xoxo.backend.backendspringboot.models.dto;

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
    private Long idColeccion;
    private String imagenProducto;
}
