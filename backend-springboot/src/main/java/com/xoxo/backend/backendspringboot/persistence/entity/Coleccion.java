package com.xoxo.backend.backendspringboot.persistence.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long idColeccion;

    @Column(name = "nombre_coleccion")
    private String nombreColeccion;

    @OneToMany(mappedBy = "coleccion")
    @JsonManagedReference
    private List<Producto> productosColeccion;
}
