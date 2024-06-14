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
public class CarritoDto {
    private Long id;
    private Long usuarioId;
}
