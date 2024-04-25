package com.Booth.Booth.mapper;

import com.Booth.Booth.dto.BuyerDto;
import com.Booth.Booth.entity.Buyer;

// Mapper class to map entity.Buyer to BuyerDto
// And to map BuyerDto to entity.Buyer

public class BuyerMapper {

    // Get Buyer entity and set it to BuyerDto
    public static BuyerDto mapToBuyerDto(Buyer buyer){
        return new BuyerDto(
                buyer.getId(),
                buyer.getUsername(),
                buyer.getEmail(),
                buyer.getPassword(),
                buyer.getPhoto(),
                buyer.getLocation(),
                buyer.getIsActive()
                // From Lombok getters
        );

    }

    // Map get buyerDto to buyer entity
    public static Buyer mapToBuyer(BuyerDto buyerDto){
        return new Buyer(
                buyerDto.getId(),
                buyerDto.getUsername(),
                buyerDto.getEmail(),
                buyerDto.getPassword(),
                buyerDto.getPhoto(),
                buyerDto.getLocation(),
                buyerDto.getIsActive()
                // From Lombok getters
        );
    }

}
