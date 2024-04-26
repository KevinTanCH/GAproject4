package com.SecurityGuy.Security.repository;

import com.SecurityGuy.Security.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// Pass product and primary key of product
public interface ProductRepository extends JpaRepository<Product, Long> {
}