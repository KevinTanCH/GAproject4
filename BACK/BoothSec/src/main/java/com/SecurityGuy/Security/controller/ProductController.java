package com.SecurityGuy.Security.controller;


import com.SecurityGuy.Security.Service.ProductService;
import com.SecurityGuy.Security.entity.Product;
import com.SecurityGuy.Security.entity.RequestBodyFrontEnd;
import com.SecurityGuy.Security.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
public class ProductController {


    @Autowired
    private ProductRepository productRepository;

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> allProduct = productRepository.findAll();

        return ResponseEntity.ok(allProduct);
    }

    @PostMapping
    public ResponseEntity<?> post1Product(@RequestBody RequestBodyFrontEnd requestBody){
        // Get product by Id using a get function.
        Product product = productService.getProductById(requestBody.getProductId());
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }

    //@valid to validation
    @PutMapping
    public ResponseEntity<Product> createProduct(@RequestBody  @Valid Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productRepository.save(product)
        );
    }
}