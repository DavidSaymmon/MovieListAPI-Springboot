package com.example.demo.dto.Movie;

import java.time.LocalDateTime;
import java.util.List;

public record MovieResponseDTO(
    Long id,
    List<String> categories,
    String name,
    String language,
    LocalDateTime releaseDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Integer duration,
    Double ratingAvg,
    Integer ratingQty
) {

}
