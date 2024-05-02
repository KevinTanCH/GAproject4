package com.SecurityGuy.Security.repository;

import com.SecurityGuy.Security.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    List<OrderList> findByUserId(Long userId);

    Optional<OrderList> findById(Long Id);

    @Query("")
    List<OrderList> findPastSalesByUserId(Long userId);
}
