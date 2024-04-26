package com.SecurityGuy.Security.authentication;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationResponse {

    private String AccessJWT;

    public AuthenticationResponse(String AccessJWT) {
        this.AccessJWT = AccessJWT;
    }

}