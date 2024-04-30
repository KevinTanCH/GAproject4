package com.SecurityGuy.Security.controller;

import com.SecurityGuy.Security.Service.OrderListService;
import com.SecurityGuy.Security.Service.ProductService;
import com.SecurityGuy.Security.Service.UserService;
import com.SecurityGuy.Security.entity.*;
import com.SecurityGuy.Security.enums.OrderStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RequestMapping
@RestController
public class OrderListController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderListService orderListService;

    @GetMapping("/order/history")
    public ResponseEntity<?> getAllPastOrders(){
        try{
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
//
//    @GetMapping("/order/salehistory")
//    public ResponseEntity<?> getAllPastSales(){
//        try{
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String usernameFromToken = (String) authentication.getPrincipal();
//            Optional<User> user = userService.findByUsername(usernameFromToken);
//            Long userId = user.get().getId();
//            List<OrderList> pastOrders = orderListService.getPastSales(userId);
//            return ResponseEntity.ok(pastOrders);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

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

    @PatchMapping("/order/buyerpatch")
    public ResponseEntity<?> patch1Order(@RequestBody @Valid FrontEndPatch1Order requestBody){
        try{
            // Check if buyer is really the same buyer.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String usernameFromToken = (String) authentication.getPrincipal();
            Optional<User> user = userService.findByUsername(usernameFromToken);
            Long userIdFromBody = requestBody.getUserId();
            Long userId = user.get().getId();
            if (userId.equals(userIdFromBody) || requestBody.getOrderStatus().equals(OrderStatus.PURCHASED)){
                OrderList createdOrder = orderListService.orderChangeStatus(requestBody);
                return ResponseEntity.ok(createdOrder);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error patching order.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/order/sellerpatch")
    public ResponseEntity<?> patch1sale(@RequestBody @Valid FrontEndPatch1Order requestBody){
        try{
            // Check if seller is really the same seller of the product.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String usernameFromToken = (String) authentication.getPrincipal();
            Optional<User> user = userService.findByUsername(usernameFromToken);
            Long userId = user.get().getId();
            Long productId = requestBody.getProductId();
            Product product = productService.getProductById(productId);
            Long userIdFromBody = product.getUser().getId();
            if (userId.equals(userIdFromBody)){
                OrderList createdOrder = orderListService.orderChangeStatus(requestBody);
                return ResponseEntity.ok(createdOrder);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error patching order.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
