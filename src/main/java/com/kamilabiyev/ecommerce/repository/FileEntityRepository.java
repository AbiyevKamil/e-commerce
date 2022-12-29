package com.kamilabiyev.ecommerce.repository;

import com.kamilabiyev.ecommerce.domain.model.entity.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileEntityRepository extends JpaRepository<ProductFile, Long> {
}
