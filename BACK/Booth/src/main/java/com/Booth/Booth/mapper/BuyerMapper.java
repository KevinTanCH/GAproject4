package com.Booth.Booth.mapper;

import com.Booth.Booth.dto.BuyerDto;
import com.Booth.Booth.entity.Buyer;

public class BuyerMapper {

    public static BuyerDto mapToBuyerDto(Buyer buyer){
        return new BuyerDto(
                buyer.getId(),
                buyer.getUsername(),
                buyer.getEmail(),
                buyer.getPassword(),
                buyer.getPhoto(),
                buyer.getLocation(),
                buyer.getIsActive()
                // Lombok getters
        );

    }public static Buyer mapToBuyer(BuyerDto buyerDto){
        return new Buyer(
                buyerDto.getId(),
                buyerDto.getUsername(),
                buyerDto.getEmail(),
                buyerDto.getPassword(),
                buyerDto.getPhoto(),
                buyerDto.getLocation(),
                buyerDto.getIsActive()
                // Lombok getters
        );
    }

}
