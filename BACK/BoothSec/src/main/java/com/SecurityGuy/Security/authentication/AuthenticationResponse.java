package com.SecurityGuy.Security.authentication;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationResponse {

    private String AccessJWT;

    // To Do make 30 days refresh token
//    private String RefrestJWT;

    public AuthenticationResponse(String AccessJWT) {
        this.AccessJWT = AccessJWT;
    }

}