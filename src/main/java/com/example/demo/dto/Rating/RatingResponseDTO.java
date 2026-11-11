package com.example.demo.dto.Rating;

import com.example.demo.dto.Movie.MovieResponseDTO;

public record RatingResponseDTO(
    MovieResponseDTO movie, Long userId, Integer userScore
) {

}
