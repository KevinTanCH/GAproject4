package com.Booth.Booth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok auto fills getter and setters
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
    // Be sure to align with entity.Buyer
}
// This is to transfer the data between client and server
// So services can use this as a response for REST APIs
// Each time we
