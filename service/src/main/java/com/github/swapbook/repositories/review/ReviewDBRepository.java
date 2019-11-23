package com.github.swapbook.repositories.review;

import com.github.swapbook.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDBRepository extends JpaRepository<Review, Integer> {
    List<Review> method1(Integer id);
}
