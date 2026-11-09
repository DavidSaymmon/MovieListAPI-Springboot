package com.example.demo.service;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Category.CategoryCreateDTO;
import com.example.demo.dto.Category.CategoryResponseDTO;
import com.example.demo.entity.Category;
import com.example.demo.infra.CategoryMapper;

import com.example.demo.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<CategoryResponseDTO> createCategory(CategoryCreateDTO request) {
        if (categoryRepository.findByName(request.name().toLowerCase()).isPresent())
            return Optional.empty();
        Category category = CategoryMapper.convertRequestToCategory(request);
        category.setName(category.getName().toLowerCase());
        categoryRepository.save(category);
        return Optional.of(CategoryMapper.convertCategoryToResponse(category));
    }
}