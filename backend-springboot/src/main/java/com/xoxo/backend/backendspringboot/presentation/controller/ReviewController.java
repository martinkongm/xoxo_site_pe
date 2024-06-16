package com.xoxo.backend.backendspringboot.presentation.controller;

import com.xoxo.backend.backendspringboot.presentation.dto.review.ReviewCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.review.ReviewResponseDto;
import com.xoxo.backend.backendspringboot.presentation.dto.review.ReviewUpdateDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Review;
import com.xoxo.backend.backendspringboot.presentation.payload.MensajeResponse;
import com.xoxo.backend.backendspringboot.service.interfaces.ReviewService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> showAll() {
        List<Review> reviews = reviewService.listAll();
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existen registros.")
                    .object(null)
                    .build(), HttpStatus.OK);
        }
        List<ReviewResponseDto> response = reviews.stream()
                .map(review -> ReviewResponseDto.builder()
                        .idReview(review.getIdReview())
                        .comentarioReview(review.getComentarioReview())
                        .ratingReview(review.getRatingReview())
                        .fechaReview(review.getFechaReview())
                        .reviewUsuario(review.getReviewUsuario().getNombre())
                        .reviewProducto(review.getReviewProducto().getNombreProducto())
                        .build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Listando registros.")
                .object(response)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Review review = reviewService.findById(id);
        if (review == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intentas buscar no existe.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        ReviewResponseDto response = ReviewResponseDto.builder()
                .idReview(review.getIdReview())
                .comentarioReview(review.getComentarioReview())
                .ratingReview(review.getRatingReview())
                .fechaReview(review.getFechaReview())
                .reviewUsuario(review.getReviewUsuario().getNombre())
                .reviewProducto(review.getReviewProducto().getNombreProducto())
                /*
                .reviewUsuario(UsuarioResponseDto.builder()
                        .idUsuario(review.getReviewUsuario().getIdUsuario())
                        .nombreUsuario(review.getReviewUsuario().getNombreUsuario())
                        .apellidoUsuario(review.getReviewUsuario().getApellidoUsuario())
                        .correoUsuario(review.getReviewUsuario().getCorreoUsuario())
                        .fechaRegistro(review.getReviewUsuario().getFechaRegistro())
                        .build())
                .reviewProducto(ProductoResponseDto.builder()
                        .idProducto(review.getReviewProducto().getIdProducto())
                        .nombreProducto(review.getReviewProducto().getNombreProducto())
                        .precioProducto(review.getReviewProducto().getPrecioProducto())
                        .tamanoProducto(review.getReviewProducto().getTamanoProducto())
                        .beneficiosProducto(review.getReviewProducto().getBeneficiosProducto())
                        .build())
                 */
                .build();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta exitosa.")
                .object(response)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<?> create(@RequestBody ReviewCreateDto reviewCreateDto) {
        try {
            Review reviewSave = reviewService.save(reviewCreateDto);
            ReviewResponseDto response = ReviewResponseDto.builder()
                    .idReview(reviewSave.getIdReview())
                    .comentarioReview(reviewSave.getComentarioReview())
                    .ratingReview(reviewSave.getRatingReview())
                    .fechaReview(reviewSave.getFechaReview())
                    .reviewUsuario(reviewSave.getReviewUsuario().getNombre())
                    .reviewProducto(reviewSave.getReviewProducto().getNombreProducto())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(response)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/review/{id}")
    public ResponseEntity<?> update(@RequestBody ReviewUpdateDto reviewUpdateDto, @PathVariable Long id) {
        try {
            if (!reviewService.existsById(id)) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
            reviewUpdateDto.setIdReview(id);
            Review reviewUpdate = reviewService.update(reviewUpdateDto);
            ReviewResponseDto response = ReviewResponseDto.builder()
                    .idReview(reviewUpdate.getIdReview())
                    .comentarioReview(reviewUpdate.getComentarioReview())
                    .ratingReview(reviewUpdate.getRatingReview())
                    .fechaReview(reviewUpdate.getFechaReview())
                    .reviewUsuario(reviewUpdate.getReviewUsuario().getNombre())
                    .reviewProducto(reviewUpdate.getReviewProducto().getNombreProducto())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Modificado correctamente")
                    .object(response)
                    .build(), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Review review = reviewService.findById(id);
            if (review == null) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta eliminar no se encuentra en la base de datos.")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
            reviewService.delete(review);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Eliminado correctamente")
                    .object(null)
                    .build(), HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
