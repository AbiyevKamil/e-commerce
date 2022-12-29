package com.kamilabiyev.ecommerce.repository;

import com.kamilabiyev.ecommerce.domain.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
