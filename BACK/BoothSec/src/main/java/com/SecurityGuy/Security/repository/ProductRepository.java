package com.SecurityGuy.Security.repository;

import com.SecurityGuy.Security.entity.Product;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

// Pass product and primary key of product
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
}