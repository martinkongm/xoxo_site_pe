package com.xoxo.backend.backendspringboot.models.repository;

import com.xoxo.backend.backendspringboot.models.entities.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
