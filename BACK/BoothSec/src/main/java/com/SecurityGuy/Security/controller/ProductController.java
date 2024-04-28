package com.SecurityGuy.Security.controller;


import com.SecurityGuy.Security.Service.ProductService;
import com.SecurityGuy.Security.config.JwtService;
import com.SecurityGuy.Security.entity.FrontEndPut1Product;
import com.SecurityGuy.Security.entity.Product;
import com.SecurityGuy.Security.entity.FrontEndPost1Product;
import com.SecurityGuy.Security.entity.User;
import com.SecurityGuy.Security.repository.ProductRepository;
import com.SecurityGuy.Security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
public class ProductController {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> allProduct = productRepository.findAll();

        return ResponseEntity.ok(allProduct);
    }

    @PostMapping
    public ResponseEntity<?> post1Product(@RequestBody FrontEndPost1Product requestBody){
        // Get product by Id using a get function.
        Optional<Product> product = productRepository.findById(requestBody.getProductId());
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }

    //@valid to validation
    @PutMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid FrontEndPut1Product requestBody){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String usernameFromToken = (String) authentication.getPrincipal();
            Long userIdFromBody = requestBody.getSellerId();
            Optional<User> user = userRepository.findByUsername(usernameFromToken);
            Long userId = user.get().getId();
            if (userId.equals(userIdFromBody)){
                Product createdProduct = productService.createProduct(requestBody);
                return ResponseEntity.ok(createdProduct);
            }            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error adding new product.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        User user = userRepository.findById(userId).
//                orElseThrow(() -> new EntityNotFoundException("Error getting seller ID"));
//        product.setUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                productRepository.save(product)
//        );
    }
}