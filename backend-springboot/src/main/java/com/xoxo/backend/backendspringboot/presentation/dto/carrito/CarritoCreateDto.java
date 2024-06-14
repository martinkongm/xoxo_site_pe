package com.xoxo.backend.backendspringboot.presentation.dto.carrito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarritoCreateDto {
    private Long usuarioId;
    private List<DetalleCreateDto> detalles;
}
