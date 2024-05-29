package com.xoxo.backend.backendspringboot.service.implementation;

import com.xoxo.backend.backendspringboot.presentation.dto.review.ReviewCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.review.ReviewUpdateDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Producto;
import com.xoxo.backend.backendspringboot.persistence.entity.Review;
import com.xoxo.backend.backendspringboot.persistence.entity.Usuario;
import com.xoxo.backend.backendspringboot.persistence.repository.ProductoRepository;
import com.xoxo.backend.backendspringboot.persistence.repository.ReviewRepository;
import com.xoxo.backend.backendspringboot.persistence.repository.UsuarioRepository;
import com.xoxo.backend.backendspringboot.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.reviewRepository = reviewRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Review> listAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Review save(ReviewCreateDto reviewCreateDto) {
        Usuario usuario = usuarioRepository.findById(reviewCreateDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(reviewCreateDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Review review = Review.builder()
                .comentarioReview(reviewCreateDto.getComentarioReview())
                .ratingReview(reviewCreateDto.getRatingReview())
                .fechaReview(reviewCreateDto.getFechaReview())
                .reviewUsuario(usuario)
                .reviewProducto(producto)
                .build();
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Review update(ReviewUpdateDto reviewUpdateDto) {
        Review review = reviewRepository.findById(reviewUpdateDto.getIdReview())
                .orElseThrow(() -> new RuntimeException("Review no encontrada"));
        /*
        Usuario usuario = usuarioRepository.findById(reviewUpdateDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(reviewUpdateDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        */
        review.setComentarioReview(reviewUpdateDto.getComentarioReview());
        review.setRatingReview(reviewUpdateDto.getRatingReview());
        review.setFechaReview(reviewUpdateDto.getFechaReview());
        //review.setReviewUsuario(usuario);
        //review.setReviewProducto(producto);

        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return reviewRepository.existsById(id);
    }
}
