package com.xoxo.backend.backendspringboot.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "reviews")
public class Review {
    @Id
    @Column(name = "id_review")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;

    @Column(name = "comentario_review")
    private String comentarioReview;

    @Column(name = "rating_review")
    private byte ratingReview;

    @Column(name = "fecha_review")
    private Date fechaReview;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario reviewUsuario;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto reviewProducto;
}
