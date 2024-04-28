package com.SecurityGuy.Security.Service;

import com.SecurityGuy.Security.entity.User;
import com.SecurityGuy.Security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User post1Seller(Long sellerId) {
        return userRepository.findById(sellerId)
                .orElseThrow( () -> new EntityNotFoundException("Not Found" + sellerId));
    }
}
