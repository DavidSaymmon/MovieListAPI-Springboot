package com.example.demo.dto.Movie;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MovieUpdateDTO(
        Set<@NotNull(message="category ids can't be null") @Positive(message="category id's must be positive integers") Long> categoryIds,
        @Size(min=1, message="movie's name must have at least 1 charachter")
        String name,
        @Size(min = 2, message="movie's language must have at least 2 ncharachters")
        String language,
        LocalDateTime releaseDate,
        @Positive(message="duration must be a positive integer")
        Integer duration
        ) {}
