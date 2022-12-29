package com.kamilabiyev.ecommerce.repository;

import com.kamilabiyev.ecommerce.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameAndIsDeleted(String username, Boolean isDeleted);

    Boolean existsByUsernameAndIsDeleted(String username, Boolean isDeleted);

    Optional<User> findByUserIdAndIsDeleted(Long userId, Boolean isDeleted);
}
