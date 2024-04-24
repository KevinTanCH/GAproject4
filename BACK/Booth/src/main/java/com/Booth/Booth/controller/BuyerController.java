package com.Booth.Booth.controller;

import com.Booth.Booth.dto.BuyerDto;
import com.Booth.Booth.service.BuyerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController to make it handle Http Request
// @RequestMapping to define the base URL
// Controller layer depends on service layer

@AllArgsConstructor
@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    // Inject BuyerRepository as a dependency
    // Requires Lombok annotation @AllArgsConstructor to auto construct
    private BuyerService buyerService;

    // Add Buyer REST API
    @PutMapping
    public ResponseEntity<BuyerDto> createBuyer(@RequestBody BuyerDto buyerDto){
        // Check if Request body is the same as entity and dto
        BuyerDto saveBuyer = buyerService.createBuyer(buyerDto);
        return new ResponseEntity<>(saveBuyer, HttpStatus.CREATED);
    }

    // Post 1 Buyer REST API
    // @PathVariable to bind variable to URL
    @PostMapping("{id}")
    public ResponseEntity<BuyerDto> post1Buyer(@PathVariable("id") Long buyerId){
        BuyerDto buyerDto = buyerService.getBuyerById(buyerId);
        return ResponseEntity.ok(buyerDto);
    }

    // Get all Buyers REST API
    @GetMapping
    public ResponseEntity<List<BuyerDto>> getAllBuyers(){
        List<BuyerDto> buyers = buyerService.getAllBuyers();
        return ResponseEntity.ok(buyers);
    }

    // Patch Buyer REST API
    @PatchMapping("{id}")
    public ResponseEntity<BuyerDto> updateBuyer(@PathVariable("id") Long buyerId, @RequestBody BuyerDto updatedBuyer){
        BuyerDto buyerDto = buyerService.updateBuyer(buyerId, updatedBuyer);
        return ResponseEntity.ok(buyerDto);
    }

    // Delete Buyer REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBuyer(@PathVariable("id") Long buyerId){
        buyerService.deleteBuyer(buyerId);
        return ResponseEntity.ok("Buyer deleted(set to inactive)");
    }


}
