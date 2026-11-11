package com.example.demo.infra;

import com.example.demo.dto.Movie.MovieResponseDTO;
import com.example.demo.dto.Rating.RatingResponseDTO;
import com.example.demo.entity.Rating;

public class RatingMapper {
    public static RatingResponseDTO converRatingToResponse(Rating rating){
        MovieResponseDTO movieResponseDTO =
            MovieMapper.convertMovieToResponse(rating.getMovie());
        return new RatingResponseDTO(
            movieResponseDTO, rating.getUser().getId(), rating.getScore()
        );
    }
}
