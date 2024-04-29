package com.SecurityGuy.Security.repository;

import com.SecurityGuy.Security.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {
}
