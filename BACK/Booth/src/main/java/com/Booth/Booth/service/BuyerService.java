package com.Booth.Booth.service;

import com.Booth.Booth.dto.BuyerDto;

import java.util.List;

// When we use the REST service we have to convert BuyerDto to entity jp buyer
// And buyer jp entity to buyerDto
// Controller layer depends on service layer

public interface BuyerService {
    // Put method
    BuyerDto createBuyer(BuyerDto buyerDto);

    // Actually a post method
    BuyerDto getBuyerById(Long buyerId);

    // Get all, return type is a list
    List<BuyerDto> getAllBuyers();

    // Patch
    BuyerDto updateBuyer(Long buyerId, BuyerDto updatedBuyer);

    // Delete
    void deleteBuyer(Long buyerId);



}
