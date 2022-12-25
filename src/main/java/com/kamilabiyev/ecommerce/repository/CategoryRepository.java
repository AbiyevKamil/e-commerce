package com.kamilabiyev.ecommerce.repository;

import com.kamilabiyev.ecommerce.domain.dto.CategoryDto;
import com.kamilabiyev.ecommerce.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByIsDeleted(Boolean isDeleted);

    Optional<Category> findByIdAndIsDeleted(Long id, boolean isDeleted);

    Optional<Category> findByNameAndIsDeleted(String name, boolean isDeleted);
}
