package com.SecurityGuy.Security.repository;

import com.SecurityGuy.Security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // Todo Maybe login by email.
//    Optional<User> findByEmail(String email);
}