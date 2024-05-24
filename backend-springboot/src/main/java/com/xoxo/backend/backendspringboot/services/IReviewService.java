package com.xoxo.backend.backendspringboot.services;

import com.xoxo.backend.backendspringboot.models.dto.review.ReviewCreateDto;
import com.xoxo.backend.backendspringboot.models.dto.review.ReviewUpdateDto;
import com.xoxo.backend.backendspringboot.models.entities.Review;

import java.util.List;

public interface IReviewService {
    List<Review> listAll();
    Review findById(Long id);
    Review save(ReviewCreateDto reviewCreateDto);
    Review update(ReviewUpdateDto reviewUpdateDto);
    void delete(Review review);
    boolean existsById(Long id);
}
