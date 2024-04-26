package com.SecurityGuy.Security.Service;

import com.SecurityGuy.Security.entity.FrontEndPut1Product;
import com.SecurityGuy.Security.entity.Product;
import com.SecurityGuy.Security.entity.User;
import com.SecurityGuy.Security.repository.ProductRepository;
import com.SecurityGuy.Security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found" + productId));
    }

    public Product createProduct(FrontEndPut1Product requestBody){
        User user = userRepository.findById(requestBody.getSellerId()).
                orElseThrow(() -> new EntityNotFoundException("Error getting seller ID"));
        Product newProduct = new Product();
        newProduct.setUser(user);
        newProduct.setName(requestBody.getName());
        newProduct.setPrice(requestBody.getPrice());
        return productRepository.save(newProduct);
    }

}
