package com.xoxo.backend.backendspringboot.presentation.dto.coleccion;

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
public class ColeccionCreateDto {
    private String nombreColeccion;
    private List<Producto> productosColeccion;
}
