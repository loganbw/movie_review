package com.logan.movie_review.dao;

import com.logan.movie_review.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao extends CrudRepository<Review, Long> {
}
