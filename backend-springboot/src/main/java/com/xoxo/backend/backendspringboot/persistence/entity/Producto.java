package com.xoxo.backend.backendspringboot.persistence.entity;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "productos")
public class Producto implements Serializable{
    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "precio_producto")
    private Double precioProducto;

    @Column(name = "tamaño_producto")
    private Integer tamanoProducto;

    @Column(name = "beneficios_producto")
    private String beneficiosProducto;

    @Column(name = "imagen_producto")
    private String imagenProducto;

    @Column(name = "stock_producto")
    private int stockProducto;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_coleccion", nullable = false)
    @JsonBackReference
    private Coleccion coleccion;

    @OneToMany(mappedBy = "reviewProducto", cascade = CascadeType.ALL)
    private List<Review> reviewsProducto;
}
