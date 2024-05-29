package com.xoxo.backend.backendspringboot.persistence.repository;

import com.xoxo.backend.backendspringboot.persistence.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
