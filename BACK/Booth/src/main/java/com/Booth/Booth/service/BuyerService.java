package com.Booth.Booth.service;

import com.Booth.Booth.dto.BuyerDto;

import java.util.List;

// When we use the REST service we have to convert BuyerDto to entity jp buyer
// And buyer jp entity to buyerDto
// Controller layer depends on service layer

public interface BuyerService {
    // Put method returns an object of BuyerDto class.
    BuyerDto createBuyer(BuyerDto buyerDto);

    // Actually a post method since single item, returns an object of BuyerDto class.
    BuyerDto getBuyerById(Long buyerId);

    // Get all, return type is a list of objects of BuyerDto class.
    List<BuyerDto> getAllBuyers();

    // Patch, return type is an object of BuyerDto class.
    BuyerDto updateBuyer(Long buyerId, BuyerDto updatedBuyer);

    // Delete, returns nothing
    void deleteBuyer(Long buyerId);

}
// Data abstraction is the process of hiding certain details and showing only essential information to the user.
// An interface is a completely "abstract class" that is used to group related methods with empty bodies
// This interface has new methods declared.
// Bodies of these methods are in the implementation class file.