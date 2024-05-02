package com.SecurityGuy.Security.controller;

import com.SecurityGuy.Security.Service.UserService;
import com.SecurityGuy.Security.config.JwtService;
import com.SecurityGuy.Security.entity.FrontEndPost1User;
import com.SecurityGuy.Security.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/seller")
    public ResponseEntity<?> post1Seller(@RequestBody FrontEndPost1User sellerId){
        // Check seller details for anyone
        Optional<User> sellerInfo = Optional.ofNullable(userService.post1UserDetail(sellerId.getUserId()));
        if (sellerInfo.isPresent()) {
            return ResponseEntity.ok(sellerInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller Not Found");
        }
    }

    @PostMapping("/buyer")
    public ResponseEntity<?> post1Buyer(@RequestBody FrontEndPost1User buyerId){
        // Check buyer details if you are seller
        Optional<User> buyerInfo = Optional.ofNullable(userService.post1UserDetail(buyerId.getUserId()));
        if (buyerInfo.isPresent()) {
            return ResponseEntity.ok(buyerInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer Not Found");
        }
    }

    @GetMapping("/self")
    public ResponseEntity<?> getSelf(@RequestBody FrontEndPost1User requstBodyUser){
        // Check if self is really self.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameFromToken = (String) authentication.getPrincipal();
        Optional<User> user = userService.findByUsername(usernameFromToken);
        Long userId = user.get().getId();
        Long userIdFromBody = requstBodyUser.getUserId();
        if (userId.equals(userIdFromBody)){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updating user.");
        }
    }

    @PatchMapping("/self")
    public ResponseEntity<?> patchSelf(@RequestBody @Valid User requstBodyUser){
        // Check if self is really self.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameFromToken = (String) authentication.getPrincipal();
        Optional<User> user = userService.findByUsername(usernameFromToken);
        Long userId = user.get().getId();
        Long userIdFromBody = requstBodyUser.getId();
        if (userId.equals(userIdFromBody)){
            User updatedUser = userService.updateUser(requstBodyUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updating user.");
        }
    }

}
