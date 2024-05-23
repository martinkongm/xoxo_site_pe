package com.xoxo.backend.backendspringboot.models.dto;

import java.io.Serializable;
import java.util.List;

import com.xoxo.backend.backendspringboot.models.entities.Producto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ColeccionDto implements Serializable{

    private Long idColeccion;

    private String nombreColeccion;

    private List<Producto> productosColeccion;

}
