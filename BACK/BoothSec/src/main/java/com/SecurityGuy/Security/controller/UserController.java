package com.SecurityGuy.Security.controller;

import com.SecurityGuy.Security.Service.UserService;
import com.SecurityGuy.Security.config.JwtService;
import com.SecurityGuy.Security.entity.FrontEndPost1User;
import com.SecurityGuy.Security.entity.Product;
import com.SecurityGuy.Security.entity.User;
import com.SecurityGuy.Security.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/seller")
    public ResponseEntity<?> post1Seller(@RequestBody FrontEndPost1User sellerId){
        Optional<User> sellerInfo = Optional.ofNullable(userService.post1Seller(sellerId.getUserId()));
        if (sellerInfo.isPresent()) {
            return ResponseEntity.ok(sellerInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller Not Found");
        }
    }

    @PostMapping("/buyer")
    public ResponseEntity<?> post1Buyer(@RequestBody FrontEndPost1User buyerId){
        Optional<User> buyerInfo = Optional.ofNullable(userService.post1Seller(buyerId.getUserId()));
        if (buyerInfo.isPresent()) {
            return ResponseEntity.ok(buyerInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer Not Found");
        }
    }

    @PatchMapping("/self")
    public ResponseEntity<?> post1Buyer(@RequestBody @Valid User requstBodyUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameFromToken = (String) authentication.getPrincipal();
        Long userIdFromBody = requstBodyUser.getId();
        Optional<User> user = userService.findByUsername(usernameFromToken);
        Long userId = user.get().getId();
        if (userId.equals(userIdFromBody)){
            User updatedUser = userService.updateUser(requstBodyUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updating user.");
        }
    }

}
