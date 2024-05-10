package com.xoxo.backend.backendspringboot.models.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "colecciones")
public class Coleccion implements Serializable{

    @Id
    @Column(name = "id_coleccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColeccion;

    @Column(name = "nombre_coleccion")
    private String nombreColeccion;

    @OneToMany(mappedBy = "coleccion")
    private List<Producto> productosColeccion;
}
