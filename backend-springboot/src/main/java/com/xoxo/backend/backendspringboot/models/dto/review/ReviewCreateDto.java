package com.xoxo.backend.backendspringboot.models.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDto {
    private String comentarioReview;
    private byte ratingReview;
    private Date fechaReview;
    private Long idUsuario;
    private Long idProducto;
}
