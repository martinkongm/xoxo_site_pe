package com.xoxo.backend.backendspringboot.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "orden_compra")
public class OrdenCompra {
    @Id
    @Column(name = "id_orden")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precio;


}
