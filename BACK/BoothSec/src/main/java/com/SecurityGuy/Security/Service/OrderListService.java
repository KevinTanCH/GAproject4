package com.SecurityGuy.Security.Service;

import com.SecurityGuy.Security.entity.FrontEndPut1Order;
import com.SecurityGuy.Security.entity.OrderList;
import com.SecurityGuy.Security.entity.Product;
import com.SecurityGuy.Security.entity.User;
import com.SecurityGuy.Security.enums.OrderStatus;
import com.SecurityGuy.Security.repository.OrderListRepository;
import com.SecurityGuy.Security.repository.ProductRepository;
import com.SecurityGuy.Security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class OrderListService {

    @Autowired
    private OrderListRepository orderListRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

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
