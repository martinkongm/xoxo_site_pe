package com.xoxo.backend.backendspringboot.presentation.dto.coleccion;

import java.io.Serializable;
import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColeccionResponseDto implements Serializable{

    private Long idColeccion;

    private String nombreColeccion;

    private List<String> productosColeccion;

}
