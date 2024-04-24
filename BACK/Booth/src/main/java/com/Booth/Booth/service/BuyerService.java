package com.Booth.Booth.service;

import com.Booth.Booth.dto.BuyerDto;

import java.util.List;

public interface BuyerService {
    BuyerDto createBuyer(BuyerDto buyerDto);

    // Actually a post method
    BuyerDto getBuyerById(Long buyerId);

    List<BuyerDto> getAllBuyers();
}
