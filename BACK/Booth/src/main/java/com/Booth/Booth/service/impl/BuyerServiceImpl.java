package com.Booth.Booth.service.impl;

import com.Booth.Booth.dto.BuyerDto;
import com.Booth.Booth.entity.Buyer;
import com.Booth.Booth.exception.ResourceNotFoundException;
import com.Booth.Booth.mapper.BuyerMapper;
import com.Booth.Booth.repository.BuyerRepository;
import com.Booth.Booth.service.BuyerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;

// @Service tells spring container to create spring bean for this class
@Service
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    // Inject BuyerRepository as a dependency
    // Requires Lombok annotation @AllArgsConstructor to auto construct
    private BuyerRepository buyerRepository;

    // To store in database
    @Override
    public BuyerDto createBuyer(BuyerDto buyerDto) {

        Buyer buyer = BuyerMapper.mapToBuyer(buyerDto);
        Buyer savedBuyer = buyerRepository.save(buyer);

        return BuyerMapper.mapToBuyerDto(savedBuyer);
    }

    @Override
    public BuyerDto getBuyerById(Long buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer not found: " + buyerId));
        // To find Buyer by Id if not throw exception

        return BuyerMapper.mapToBuyerDto(buyer);
    }

    @Override
    public List<BuyerDto> getAllBuyers() {
        // findAll returns a list
        List<Buyer> buyers = buyerRepository.findAll();
        // We have a list, so we convert buyer to buyerDto
        // Use map to map 1 object to another object
        return buyers.stream().map((buyer)-> BuyerMapper.mapToBuyerDto(buyer)).collect(Collectors.toList());
    }

    @Override
    public BuyerDto updateBuyer(Long buyerId, BuyerDto updatedBuyer) {

        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow(
                ()-> new ResourceNotFoundException(("Buyer not found" + buyerId))
        );

        //Get new data from updated then set it to entity.
        buyer.setUsername(updatedBuyer.getUsername());
        buyer.setEmail(updatedBuyer.getEmail());
        buyer.setPassword(updatedBuyer.getPassword());
        buyer.setPhoto(updatedBuyer.getPhoto());
        buyer.setLocation(updatedBuyer.getLocation());
        buyer.setIsActive(updatedBuyer.getIsActive());

        // Perform Update
        Buyer updatedBuyerObj = buyerRepository.save(buyer);

        return BuyerMapper.mapToBuyerDto(updatedBuyerObj);
    }

    @Override
    public void deleteBuyer(Long buyerId) {

        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow(
                ()-> new ResourceNotFoundException(("Buyer not found" + buyerId))
        );

        buyerRepository.deleteById(buyerId);
    }
}
