package com.kamilabiyev.ecommerce.service.data;


import com.kamilabiyev.ecommerce.domain.model.dto.CategoryDto;
import com.kamilabiyev.ecommerce.domain.model.request.category.CreateCategoryRequest;
import com.kamilabiyev.ecommerce.domain.model.request.category.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll();

    CategoryDto getById(Long id);

    void delete(Long id);

    Long create(CreateCategoryRequest createCategoryRequest);

    Long update(Long id, UpdateCategoryRequest updateCategoryRequest);
}
