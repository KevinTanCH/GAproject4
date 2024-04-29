package com.SecurityGuy.Security.Service;

import com.SecurityGuy.Security.entity.Product;
import com.SecurityGuy.Security.entity.User;
import com.SecurityGuy.Security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User post1Seller(Long sellerId) {
        return userRepository.findById(sellerId)
                .orElseThrow( () -> new EntityNotFoundException("Not Found" + sellerId));
    }

    public Optional<User> findByUsername(String usernameFromToken) {
        return Optional.ofNullable(userRepository.findByUsername(usernameFromToken)
                .orElseThrow(() -> new EntityNotFoundException("Not Found" + usernameFromToken)));
    }

    public User updateUser(User requstBodyUser) {
        Long userId = requstBodyUser.getId();
        User userToBePatched = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not Found" + userId));

        userToBePatched.setName(requstBodyUser.getName());
        userToBePatched.setUsername(requstBodyUser.getUsername());
        userToBePatched.setEmail(requstBodyUser.getEmail());
        userToBePatched.setPassword(passwordEncoder.encode(requstBodyUser.getPassword()));
        userToBePatched.setPhoto(requstBodyUser.getPhoto());
        userToBePatched.setLocation(requstBodyUser.getLocation());
        userToBePatched.setIsActive(requstBodyUser.getIsActive());


        return userRepository.save(userToBePatched);
    }
}
