package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Movie.MovieResponseDTO;
import com.example.demo.entity.Rating;
import com.example.demo.entity.RatingKey;
import com.example.demo.entity.User;
import com.example.demo.infra.MovieMapper;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.RatingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService {
    
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    @Transactional 
    public Optional<MovieResponseDTO> rateMovie(User user, Long movieId, Integer score) {
        return movieRepository.findById(movieId).map(movie -> {
            RatingKey key = new RatingKey(user.getId(), movieId);
            Rating rating = ratingRepository.findById(key).orElse(null);

            if (rating == null) {
                movie.addRating(score);
                rating = new Rating(key, user, movie, score);
                ratingRepository.save(rating);
            } else {
                movie.updateRating(rating.getScore(), score);
                rating.setScore(score);
            }
            return MovieMapper.convertMovieToResponse(movie);
        });
    }
}
