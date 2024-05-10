package com.xoxo.backend.backendspringboot.models.entities;

import java.io.Serializable;
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
    private Integer idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "precio_producto")
    private Double precioProducto;

    @ManyToOne
    @JoinColumn(name = "id_coleccion")
    private Coleccion coleccion;
    
}
