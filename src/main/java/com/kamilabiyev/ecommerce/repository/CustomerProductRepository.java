package com.kamilabiyev.ecommerce.repository;

import com.kamilabiyev.ecommerce.domain.model.entity.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Long> {
}
