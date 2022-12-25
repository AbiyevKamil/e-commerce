package com.kamilabiyev.ecommerce.controller;

import com.kamilabiyev.ecommerce.domain.request.category.CreateCategoryRequest;
import com.kamilabiyev.ecommerce.domain.request.category.UpdateCategoryRequest;
import com.kamilabiyev.ecommerce.service.data.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        return ResponseEntity.ok().body(categoryService.create(createCategoryRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity create(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest, @PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.update(id, updateCategoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
