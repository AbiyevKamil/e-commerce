package com.kamilabiyev.ecommerce.service.data.impl;

import com.kamilabiyev.ecommerce.domain.model.dto.CategoryDto;
import com.kamilabiyev.ecommerce.domain.exception.DataExistsException;
import com.kamilabiyev.ecommerce.domain.exception.DataNotFoundException;
import com.kamilabiyev.ecommerce.domain.model.request.category.CreateCategoryRequest;
import com.kamilabiyev.ecommerce.domain.model.request.category.UpdateCategoryRequest;
import com.kamilabiyev.ecommerce.repository.CategoryRepository;
import com.kamilabiyev.ecommerce.service.data.CategoryService;
import com.kamilabiyev.ecommerce.service.utils.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll() {
        var categories = categoryRepository.findAllByIsDeleted(false);
        var model = categoryMapper.toCategoryDto(categories);
        return model;
    }

    @SneakyThrows
    @Override
    public CategoryDto getById(Long id) {
        var category = categoryRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new DataNotFoundException("Category#" + id + " not found."));
        return categoryMapper.toCategoryDto(category);
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        var category = categoryRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new DataNotFoundException("Category#" + id + " not found."));
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Long create(CreateCategoryRequest createCategoryRequest) {
        var category = categoryRepository.findByNameAndIsDeleted(createCategoryRequest.getName(), false);
        if (category.isPresent()) category.get().getId();
        var created = categoryRepository.save(categoryMapper.toCategory(createCategoryRequest));
        return created.getId();
    }

    @SneakyThrows
    @Transactional
    @Override
    public Long update(Long id, UpdateCategoryRequest updateCategoryRequest) {
        var categoryByNameOptional = categoryRepository.findByNameAndIsDeleted(updateCategoryRequest.getName(), false);
        if (categoryByNameOptional.isPresent()) {
            var category = categoryByNameOptional.get();
            if (category.getId() != id)
                throw new DataExistsException("Category with name:" + updateCategoryRequest.getName() + " is already exists.");
        }
        var categoryById = categoryRepository.findByIdAndIsDeleted(id, false).orElseThrow(() ->
                new DataNotFoundException("Category#" + id + " not found."));
        categoryById.setName(updateCategoryRequest.getName());
        categoryRepository.save(categoryById);
        return id;
    }
}
