package com.kamilabiyev.ecommerce.repository;

import com.kamilabiyev.ecommerce.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer as c where c.user.userId = ?1 and c.isDeleted = ?2")
    Optional<Customer> findByUserIdAndIsDeleted(Long userId, Boolean isDeleted);
}
