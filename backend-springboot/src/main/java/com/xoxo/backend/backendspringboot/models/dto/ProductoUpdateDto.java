package com.xoxo.backend.backendspringboot.models.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoUpdateDto {
    private Long idProducto;
    private String nombreProducto;
    private Double precioProducto;
    private Integer tamanoProducto;
    private String beneficiosProducto;
    private String imagenProducto;
    private Long idColeccion;
}