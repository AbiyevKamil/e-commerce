package com.kamilabiyev.ecommerce.repository;

import com.kamilabiyev.ecommerce.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query("select c from Seller as c where c.user.userId = ?1 and c.isDeleted = ?2")
    Optional<Seller> findByUserIdAndIsDeleted(Long userId, Boolean isDeleted);
}
