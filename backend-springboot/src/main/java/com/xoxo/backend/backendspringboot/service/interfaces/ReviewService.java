package com.xoxo.backend.backendspringboot.service.interfaces;

import com.xoxo.backend.backendspringboot.presentation.dto.review.ReviewCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.review.ReviewUpdateDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> listAll();
    Review findById(Long id);
    Review save(ReviewCreateDto reviewCreateDto);
    Review update(ReviewUpdateDto reviewUpdateDto);
    void delete(Review review);
    boolean existsById(Long id);
}
