package com.xoxo.backend.backendspringboot.models.dto;

import java.util.Date;

import com.xoxo.backend.backendspringboot.models.entities.Cliente;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PedidoDto {

    private Integer idPedido;

    private Date fechaPedido;

    private Double totalPedido;

    private Cliente clientePedido;

}
