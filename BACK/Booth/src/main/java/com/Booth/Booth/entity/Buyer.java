package com.Booth.Booth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String password;
    private String photo;
    private String location;
    private Boolean isActive;
    // Be sure to align with dto.BuyerDto


}
