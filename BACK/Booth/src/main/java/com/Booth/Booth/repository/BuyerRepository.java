package com.Booth.Booth.repository;

import com.Booth.Booth.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

// No need @Repository and @Transactional
// because JpaRepository inherits from SimpleJpaRepository
// JpaRepository is generic interface, requires 2 parameters,
// type of entity (entity.Buyer) and type of primary key (entity.Buyer.id)
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
// Because BuyerRepository extends from JpaRepository
// it will inherit all the CRUD methods
// All the public in the SimpleJpaRepository has transactional methods