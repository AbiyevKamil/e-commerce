package com.kamilabiyev.ecommerce.service.utils.mapper;

import com.kamilabiyev.ecommerce.domain.model.dto.CategoryDto;
import com.kamilabiyev.ecommerce.domain.model.entity.Category;
import com.kamilabiyev.ecommerce.domain.model.request.category.CreateCategoryRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public List<CategoryDto> toCategoryDto(List<Category> categories) {
        return categories.stream().map(this::toCategoryDto).collect(Collectors.toList());
    }

    public Category toCategory(CreateCategoryRequest categoryRequest) {
        return Category.builder().name(categoryRequest.getName()).build();
    }
}
