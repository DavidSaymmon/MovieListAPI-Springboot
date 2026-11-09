package com.example.demo.dto.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryUpdateDTO(
    @NotBlank(message="category's name must be not null and have at least 1 non-space charachter")
    String name) {
}
