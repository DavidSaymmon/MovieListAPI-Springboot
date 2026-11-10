package com.example.demo.dto.Movie;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MovieCreateDTO (

Set<@NotNull @Positive Long> categoryIds,
@NotBlank(message="name is a mandatory field")
String name,
@NotBlank(message="language is a mandatory field")
@Size(min=2, message="movie's language must have at least 2 non-space charachter")
String language,
@NotNull(message="releaseDate is a mandatory field")
LocalDateTime releaseDate,
@Positive(message="duration must be a positive integer")
@NotNull(message="duration is a mandatory field")
Integer duration) {}
