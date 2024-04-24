package com.Booth.Booth.controller;

import com.Booth.Booth.dto.BuyerDto;
import com.Booth.Booth.service.BuyerService;
import lombok.AllArgsConstructor;
import org.hibernate.boot.jaxb.internal.stax.BufferedXMLEventReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private BuyerService buyerService;

    // Add Buyer REST API
    @PutMapping
    public ResponseEntity<BuyerDto> createBuyer(@RequestBody BuyerDto buyerDto){
        BuyerDto saveBuyer = buyerService.createBuyer(buyerDto);
        return new ResponseEntity<>(saveBuyer, HttpStatus.CREATED);
    }

    // Post 1 Buyer REST API
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


}
