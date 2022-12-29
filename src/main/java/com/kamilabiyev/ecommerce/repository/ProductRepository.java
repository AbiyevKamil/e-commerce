package com.kamilabiyev.ecommerce.repository;

import com.kamilabiyev.ecommerce.domain.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
