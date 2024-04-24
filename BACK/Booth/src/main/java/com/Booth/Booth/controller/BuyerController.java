package com.Booth.Booth.controller;

import com.Booth.Booth.dto.BuyerDto;
import com.Booth.Booth.service.BuyerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private BuyerService buyerService;

    // Build Add Buyer REST API
    @PutMapping
    public ResponseEntity<BuyerDto> createBuyer(@RequestBody BuyerDto buyerDto){
        BuyerDto saveBuyer = buyerService.createBuyer(buyerDto);
        return new ResponseEntity<>(saveBuyer, HttpStatus.CREATED);
    }


}
