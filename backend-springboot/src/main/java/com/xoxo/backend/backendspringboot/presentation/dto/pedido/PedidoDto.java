package com.xoxo.backend.backendspringboot.presentation.dto.pedido;

import java.util.Date;

import com.xoxo.backend.backendspringboot.persistence.entity.Usuario;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PedidoDto {

    private Long idPedido;

    private Date fechaPedido;

    private Double totalPedido;

    private Usuario usuarioPedido;

}
