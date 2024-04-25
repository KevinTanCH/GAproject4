package com.Booth.Booth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok autofill getter and setters
// Lombok autofill constructors so no need to write constructor method.
// @Entity annotation to make Buyer a JP entity
// And to specify the class as a Jp entity
// @Table to specify SQL table and details
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buyers")
public class Buyer {

    // Configure primary key and primary key type
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Specify the column name. Actually don't need here.
    @Column(name = "username")
    private String username;

    // To make email not null and all Emails in table unique
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String password;
    private String photo;
    private String location;
    private Boolean isActive;
    // These are attributes of Buyer class.
    // Private because should only be used in this class.
    // Be sure to align with dto.BuyerDto


}
