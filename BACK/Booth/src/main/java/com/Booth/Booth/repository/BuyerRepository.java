package com.Booth.Booth.repository;

import com.Booth.Booth.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
// No need @Repository and @Transactional because JpaRepository inherits from SimpleJpaRepository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
