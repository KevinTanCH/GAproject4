package com.Booth.Booth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// Lombok autofill getter and setters
// Lombok autofill constructors so no need to write constructor method.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String photo;
    private String location;
    private Boolean isActive;
    // These are attributes of BuyerDto class.
    // Private because should only be used in this class.
    // Be sure to align with entity.Buyer
}
// This is to transfer the data between client and server
// So services can use this as a response for REST APIs
// Each time we
