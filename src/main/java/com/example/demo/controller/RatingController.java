package com.example.demo.controller;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Movie.MovieResponseDTO;
import com.example.demo.dto.Rating.RatingRequestDTO;
import com.example.demo.entity.User;
import com.example.demo.infra.Result;
import com.example.demo.service.RatingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor 
public class RatingController {

    private final RatingService ratingService;

    @PatchMapping("/movies/{id}/movieRating")
    public ResponseEntity<?> rateMovie(@AuthenticationPrincipal User user, @PathVariable("id") Long movieId,
            @Valid @RequestBody RatingRequestDTO request) {
        Optional<MovieResponseDTO> responseOptional = ratingService.rateMovie(user, movieId, request.score());
        if (responseOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "NOT_FOUND",
                    "message", "A Movie with id " + movieId + " was not found in database", "code", "MOVIE_NOT_FOUND"));
        }
        return ResponseEntity.ok(responseOptional.get());
    }


    @DeleteMapping("/movies/{id}/movieRating")
    public ResponseEntity<?> deleteRating(@AuthenticationPrincipal User user, @PathVariable("id") Long movieId) {
        Result<Void> result = ratingService.deleteRatingById(user, movieId);
        if (result instanceof Result.NotFound())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "NOT_FOUND", "message",
                    "A Rating with userId " + user.getId() + " and movieId " + movieId
                            + " was not found in the database",
                    "code", "RATING_NOT_FOUND"));
        return ResponseEntity.noContent().build();
    }

}
