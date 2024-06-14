package com.xoxo.backend.backendspringboot.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "carrito_compras")
public class Carrito implements Serializable {
    @Id
    @Column(name = "id_carrito")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario") //Puede ser null
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito")
    private List<DetalleCarrito> detallesCarritos;
}
