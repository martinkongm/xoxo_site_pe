package com.xoxo.backend.backendspringboot.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "direcciones")
public class Direccion {
    @Id
    @Column(name = "id_direccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private byte apartamento;

    @OneToMany(mappedBy = "direccion")
    private List<Pedido> pedido;
}
