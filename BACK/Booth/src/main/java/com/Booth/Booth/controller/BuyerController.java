package com.Booth.Booth.controller;

import com.Booth.Booth.dto.BuyerDto;
import com.Booth.Booth.service.BuyerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private BuyerService buyerService;

    // Build Add Buyer REST API
    @PostMapping
    public ResponseEntity<BuyerDto> createBuyer(@RequestBody BuyerDto buyerDto){
        BuyerDto saveBuyer = buyerService.createBuyer(buyerDto);
        return new ResponseEntity<>(saveBuyer, HttpStatus.CREATED);
    }


}
