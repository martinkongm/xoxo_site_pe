package com.xoxo.backend.backendspringboot.presentation.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private Long idReview;
    private String comentarioReview;
    private byte ratingReview;
    private Date fechaReview;
    private String reviewUsuario;
    private String reviewProducto;
    //private UsuarioResponseDto reviewUsuario;
    //private ProductoResponseDto reviewProducto;
}
