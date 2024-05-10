package com.xoxo.backend.backendspringboot.models.dto;

import java.io.Serializable;

import com.xoxo.backend.backendspringboot.models.entities.Coleccion;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ProductoDto implements Serializable{

    private Integer idProducto;

    private String nombreProducto;

    private Double precioProducto;

    private Coleccion coleccion;

}
