package com.example.demo.infra;

import com.example.demo.dto.Movie.MovieCreateDTO;
import com.example.demo.dto.Movie.MovieResponseDTO;
import com.example.demo.entity.Movie;

public class MovieMapper {

    public static Movie convertRequestToMovie(MovieCreateDTO request) {
        return new Movie(
                request.name(),
                request.language(),
                request.releaseDate(),
                request.duration()
        );
    }
    public static MovieResponseDTO convertMovieToResponse(Movie movie){
        return new MovieResponseDTO(
            movie.getId(),
            movie.getCategories()
                .stream()
                .map(movieCategory -> movieCategory.getCategory().getName())
                .toList(),
            movie.getName(),
            movie.getLanguage(),
            movie.getReleaseDate(),
            movie.getCreatedAt(),
            movie.getUpdatedAt(),
            movie.getDuration(),
            movie.getRatingAvg(),
            movie.getRatingQty()
        );
    }
}
