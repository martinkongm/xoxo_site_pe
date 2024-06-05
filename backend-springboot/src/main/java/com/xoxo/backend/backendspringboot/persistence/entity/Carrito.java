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
@Table(name = "carrito_compras")
public class Carrito {
    @Id
    @Column(name = "id_carrito")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario") //Puede ser null
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito")
    private List<DetalleCarrito> detallesCarrito;
}
