package com.example.demo.infra;

import com.example.demo.dto.Category.CategoryCreateDTO;
import com.example.demo.dto.Category.CategoryResponseDTO;
import com.example.demo.entity.Category;

public class CategoryMapper {
    public static CategoryResponseDTO convertCategoryToResponse(Category category){
        return new CategoryResponseDTO(category.getName(), category.getId());
    }
    public static Category convertRequestToCategory(CategoryCreateDTO request){
        Category category = new Category();
        category.setName(request.name());
        return category;
    }
}
