package com.example.demo.service;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Category.CategoryCreateDTO;
import com.example.demo.dto.Category.CategoryResponseDTO;
import com.example.demo.dto.Category.CategoryUpdateDTO;
import com.example.demo.entity.Category;
import com.example.demo.infra.CategoryMapper;
import com.example.demo.infra.Result;
import com.example.demo.repository.CategoryRepository;

import jakarta.transaction.Transactional;
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
    
    @Transactional 
    public Result<CategoryResponseDTO> updateCategoryById(Long id, CategoryUpdateDTO request) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty())
            return new Result.NotFound<>();
        if(categoryRepository.findByName(request.name()).isPresent())
            return new Result.Conflict<>();
        Category category = categoryOptional.get();
        category.setName(request.name());
        return new Result.Success<CategoryResponseDTO>(CategoryMapper.convertCategoryToResponse(category));
    }
        public Result<Void> deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            return new Result.NotFound<>();
        }
        categoryRepository.deleteById(id);
        return new Result.Success<>();
    }

}