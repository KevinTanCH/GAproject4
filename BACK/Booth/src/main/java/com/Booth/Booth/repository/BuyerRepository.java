package com.Booth.Booth.repository;

import com.Booth.Booth.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

// No need @Repository and @Transactional
// because JpaRepository inherits from SimpleJpaRepository
// JpaRepository is generic interface, requires 2 parameters,
// The entity (entity.Buyer) and type of primary key (entity.Buyer.id is of long datatype)
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
// Because BuyerRepository extends from JpaRepository
// it will inherit all the CRUD methods
// All the public in the SimpleJpaRepository has transactional methods

// Data abstraction is the process of hiding certain details and showing only essential information to the user.
// An interface is a completely "abstract class" that is used to group related methods with empty bodies
// This interface has no new methods but inherits from JpaRepository