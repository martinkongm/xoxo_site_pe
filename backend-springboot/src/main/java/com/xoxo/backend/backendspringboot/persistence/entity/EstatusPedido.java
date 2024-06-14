package com.xoxo.backend.backendspringboot.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "estatus_orden")
public class EstatusPedido implements Serializable {
    @Id
    @Column(name = "id_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status; //En proceso | Delivery en progreso | Entregado

    @OneToMany(mappedBy = "estatusPedido")
    private List<Pedido> pedidos;
}
