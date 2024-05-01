package com.SecurityGuy.Security.entity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FrontEndPut1Product {

    // Not be blank
    @NotBlank
    private  String name;

    // Validation. The min price
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    private String description;
    private String photo;
    private Long stock;

    private Boolean isAvailable;

    private Long sellerId;

}
