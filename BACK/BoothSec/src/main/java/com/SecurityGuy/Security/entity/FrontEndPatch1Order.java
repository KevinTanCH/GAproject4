package com.SecurityGuy.Security.entity;

import com.SecurityGuy.Security.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FrontEndPatch1Order {

    private Long id;
    private Long productId;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


}
