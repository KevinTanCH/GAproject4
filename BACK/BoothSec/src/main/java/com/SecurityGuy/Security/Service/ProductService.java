package com.SecurityGuy.Security.Service;

import com.SecurityGuy.Security.entity.Product;
import com.SecurityGuy.Security.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class ProductService {

    private ProductRepository productRepository;


    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found" + productId));
    }
}
