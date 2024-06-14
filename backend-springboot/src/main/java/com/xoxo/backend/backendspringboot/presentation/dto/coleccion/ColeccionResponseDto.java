package com.xoxo.backend.backendspringboot.presentation.dto.coleccion;

import java.io.Serializable;
import java.util.List;

import com.xoxo.backend.backendspringboot.persistence.entity.Producto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ColeccionDto implements Serializable{

    private Long idColeccion;

    private String nombreColeccion;

    private List<String> productosColeccion;

}