package com.SecurityGuy.Security.Service;

import com.SecurityGuy.Security.entity.*;
import com.SecurityGuy.Security.enums.OrderStatus;
import com.SecurityGuy.Security.enums.Role;
import com.SecurityGuy.Security.repository.OrderListRepository;
import com.SecurityGuy.Security.repository.ProductRepository;
import com.SecurityGuy.Security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
        List<OrderList> pastOrders = orderListRepository.findByUserId(userId);
        return pastOrders;
    }

    public List<OrderList> getPastSales(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new EntityNotFoundException("Error getting buyer ID"));
        List<OrderList> pastSales = orderListRepository.findPastSalesByUserId(userId);
        return pastSales;

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
        newOrder.setTimeOrdered(LocalDateTime.now());
        newOrder.setLocationFrom(product.getUser().getLocation());
        newOrder.setLocationTo(user.getLocation());
        newOrder.setOrderStatus(OrderStatus.PENDING_PURCHASE);
        newOrder.setTimeDelivered(null);

        product.setStock(product.getStock() - 1);

        return orderListRepository.save(newOrder);
    }


    public OrderList orderChangeStatus(FrontEndPatch1Order requestBody) {
        User user = userRepository.findById(requestBody.getUserId()).
                orElseThrow(() -> new EntityNotFoundException("Error getting Buyer ID"));
        OrderList orderToBePatched = orderListRepository.findById(requestBody.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not Found" + requestBody.getId()));

        orderToBePatched.setOrderStatus(requestBody.getOrderStatus());

        // If seller delivers products.
        if(requestBody.getOrderStatus().equals(OrderStatus.DELIVERED)){
            orderToBePatched.setTimeDelivered(LocalDateTime.now());
        }

        return orderListRepository.save(orderToBePatched);

    }

}
