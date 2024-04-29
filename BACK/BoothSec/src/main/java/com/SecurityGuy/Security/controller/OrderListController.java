package com.SecurityGuy.Security.controller;

import com.SecurityGuy.Security.Service.OrderListService;
import com.SecurityGuy.Security.Service.UserService;
import com.SecurityGuy.Security.entity.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping
@RestController
public class OrderListController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderListService orderListService;

    @GetMapping("/order/history")
    public ResponseEntity<?> getAllPastOrders(){
        try{
            // Check if buyer is really the same buyer.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String usernameFromToken = (String) authentication.getPrincipal();
            Optional<User> user = userService.findByUsername(usernameFromToken);
            Long userId = user.get().getId();
            List<OrderList> pastOrders = orderListService.getPastOrders(userId);
            return ResponseEntity.ok(pastOrders);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/purchase")
    public ResponseEntity<?> put1Order(@RequestBody FrontEndPut1Order requestBody){
        try{
            // Check if buyer is really the same buyer.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String usernameFromToken = (String) authentication.getPrincipal();
            Optional<User> user = userService.findByUsername(usernameFromToken);
            Long userIdFromBody = requestBody.getBuyerId();
            Long userId = user.get().getId();
            if (userId.equals(userIdFromBody)){
                OrderList createdOrder = orderListService.createOrder(requestBody);
                return ResponseEntity.ok(createdOrder);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error adding new order.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
