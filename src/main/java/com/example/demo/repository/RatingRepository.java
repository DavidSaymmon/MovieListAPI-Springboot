package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Rating;
import com.example.demo.entity.RatingKey;

public interface RatingRepository extends JpaRepository<Rating, RatingKey> {

    List<Rating> findByUser_Id(Long id);

}
