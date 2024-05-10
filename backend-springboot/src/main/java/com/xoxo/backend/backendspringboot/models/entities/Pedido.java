package com.xoxo.backend.backendspringboot.models.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "pedidos")
public class Pedido implements Serializable{

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    @Column(name = "total_pedido")
    private Double totalPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente clientePedido;

}
