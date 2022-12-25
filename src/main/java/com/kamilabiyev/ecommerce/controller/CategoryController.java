package com.kamilabiyev.ecommerce.controller;

import com.kamilabiyev.ecommerce.domain.request.category.CreateCategoryRequest;
import com.kamilabiyev.ecommerce.domain.request.category.UpdateCategoryRequest;
import com.kamilabiyev.ecommerce.service.data.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @SecurityRequirements
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity create(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        return ResponseEntity.ok().body(categoryService.create(createCategoryRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity update(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest, @PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.update(id, updateCategoryRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
