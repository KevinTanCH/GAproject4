package com.SecurityGuy.Security.entity;

import com.SecurityGuy.Security.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "order_list")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product product;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;

    private LocalDate dateOrdered;
    private LocalTime TimeOrdered;

    @DecimalMin(value = "0.01")
    private BigDecimal amountPaid;

    private String locationFrom;
    private String locationTo;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
