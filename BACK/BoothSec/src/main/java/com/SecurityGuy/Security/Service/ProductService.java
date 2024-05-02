package com.SecurityGuy.Security.Service;

import com.SecurityGuy.Security.entity.FrontEndPatch1Product;
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
        newProduct.setDescription(requestBody.getDescription());
        newProduct.setPhoto(requestBody.getPhoto());
        newProduct.setStock(requestBody.getStock());
        newProduct.setIsAvailable(requestBody.getIsAvailable());

        return productRepository.save(newProduct);
    }

    public Product updateProduct(FrontEndPatch1Product requestBody) {
        Long productId = requestBody.getProductId();
        Product productToBePatched = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found" + productId));

        productToBePatched.setName(requestBody.getName());
        productToBePatched.setPrice(requestBody.getPrice());
        productToBePatched.setPhoto(requestBody.getPhoto());
        productToBePatched.setStock(requestBody.getStock());
        productToBePatched.setIsAvailable(requestBody.getIsAvailable());

        return productRepository.save(productToBePatched);
    }

    public Product deleteProduct(Long productId) {
        Product productToBeDeleted = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found" + productId));

        // Doesn't really delete but set as "unavailable"
        productToBeDeleted.setIsAvailable(false);

        return productRepository.save(productToBeDeleted);

    }
}
