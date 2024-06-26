package com.SecurityGuy.Security.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
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

    private String description;
    private String photo;
    private Long stock;

    // Link Many products to one seller (fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean isAvailable;

}
