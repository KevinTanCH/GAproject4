package com.SecurityGuy.Security.Service;

import com.SecurityGuy.Security.entity.*;
import com.SecurityGuy.Security.enums.OrderStatus;
import com.SecurityGuy.Security.repository.OrderListRepository;
import com.SecurityGuy.Security.repository.ProductRepository;
import com.SecurityGuy.Security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderListService {

    @Autowired
    private OrderListRepository orderListRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderList> getPastOrders(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("Error getting buyer ID"));

        System.out.println("user id"+userId);
        List<OrderList> pastOrders = orderListRepository.findByUserId(userId);
        System.out.println("past Orders"+pastOrders);
        return pastOrders;
    }


    public OrderList createOrder(FrontEndPut1Order requestBody) {
        User user = userRepository.findById(requestBody.getBuyerId()).
                orElseThrow(() -> new EntityNotFoundException("Error getting buyer ID"));
        Product product = productRepository.findById(requestBody.getProductId()).
                orElseThrow(() -> new EntityNotFoundException("Error getting product ID"));
        OrderList newOrder = new OrderList();
        newOrder.setProduct(product);
        newOrder.setUser(user);
        newOrder.setAmountPaid(product.getPrice());
        newOrder.setDateOrdered(LocalDate.now());
        newOrder.setTimeOrdered(LocalTime.now());
        newOrder.setLocationFrom(product.getUser().getLocation());
        newOrder.setLocationTo(user.getLocation());
        newOrder.setOrderStatus(OrderStatus.PENDING_PURCHASE);
        return orderListRepository.save(newOrder);
    }

}
