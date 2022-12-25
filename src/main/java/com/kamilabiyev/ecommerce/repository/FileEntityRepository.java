package com.kamilabiyev.ecommerce.repository;

import com.kamilabiyev.ecommerce.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {
}
