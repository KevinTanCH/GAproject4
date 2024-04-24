package com.Booth.Booth.service.impl;

import com.Booth.Booth.dto.BuyerDto;
import com.Booth.Booth.entity.Buyer;
import com.Booth.Booth.mapper.BuyerMapper;
import com.Booth.Booth.repository.BuyerRepository;
import com.Booth.Booth.service.BuyerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

// @Service tells spring container to create spring bean
@Service
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private BuyerRepository buyerRepository;

    @Override
    public BuyerDto createBuyer(BuyerDto buyerDto) {

        Buyer buyer = BuyerMapper.mapToBuyer(buyerDto);
        Buyer savedBuyer = buyerRepository.save(buyer);

        return BuyerMapper.mapToBuyerDto(savedBuyer);
    }
}
