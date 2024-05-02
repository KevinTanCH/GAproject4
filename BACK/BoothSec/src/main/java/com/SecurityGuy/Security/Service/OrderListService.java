package com.SecurityGuy.Security.Service;

import com.SecurityGuy.Security.entity.*;
import com.SecurityGuy.Security.enums.OrderStatus;
import com.SecurityGuy.Security.repository.OrderListRepository;
import com.SecurityGuy.Security.repository.ProductRepository;
import com.SecurityGuy.Security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        // Take out 1 item because buying it. I should add feature to buy multiple of same product.
        if (product.getStock() > 0) {
            product.setStock(product.getStock() - 1);
        }

        return orderListRepository.save(newOrder);
    }


    public OrderList orderChangeStatus(FrontEndPatch1Order requestBody) {
        User user = userRepository.findById(requestBody.getUserId()).
                orElseThrow(() -> new EntityNotFoundException("Error getting Buyer ID"));
        OrderList orderToBePatched = orderListRepository.findById(requestBody.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not Found" + requestBody.getId()));

        // For users to update status. Only patch BUYER can do is "PURCHASED"
        orderToBePatched.setOrderStatus(requestBody.getOrderStatus());

        // If seller delivers products. Update Time delivered
        if(requestBody.getOrderStatus().equals(OrderStatus.DELIVERED)){
            orderToBePatched.setTimeDelivered(LocalDateTime.now());
        }

        return orderListRepository.save(orderToBePatched);

    }

}
