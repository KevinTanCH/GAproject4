package com.SecurityGuy.Security.entity;

import com.SecurityGuy.Security.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order_list")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Some errors for fetch type
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;

    private LocalDateTime TimeOrdered;

    @DecimalMin(value = "0.01")
    private BigDecimal amountPaid;

    private String locationFrom;
    private String locationTo;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime TimeDelivered;
}
