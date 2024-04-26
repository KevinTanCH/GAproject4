package com.SecurityGuy.Security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Product {

    // Pass null to tell the front end we are already doing it
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Not be blank
    @NotBlank
    private  String name;

    // Validation. The min price
    @DecimalMin(value = "0.01")
    private BigDecimal price;

}
