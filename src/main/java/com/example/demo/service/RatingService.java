package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Movie.MovieResponseDTO;
import com.example.demo.dto.Rating.RatingResponseDTO;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Rating;
import com.example.demo.entity.RatingKey;
import com.example.demo.entity.User;
import com.example.demo.infra.MovieMapper;
import com.example.demo.infra.RatingMapper;
import com.example.demo.infra.Result;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.RatingRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService {
    
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
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
    
    @Transactional
    public Result<Void> deleteRatingById(User user, Long movieId) {
        RatingKey key = new RatingKey(user.getId(), movieId);
        Rating rating = ratingRepository.findById(key).orElse(null);
        if (rating == null) {
            return new Result.NotFound<>();
        }
        ratingRepository.deleteById(key);
            Movie movie = rating.getMovie();
            movie.removeRating(rating.getScore());
        return new Result.Success<>(null);
    }

    @Transactional
    public Result<List<RatingResponseDTO>> getRatingsByUser(Long userId){
        if(!userRepository.existsById(userId))
            return new Result.NotFound<>();
        return new Result.Success<>(
        ratingRepository.findByUser_Id(userId)
        .stream().map(RatingMapper::converRatingToResponse).toList()
        );
    }
}
