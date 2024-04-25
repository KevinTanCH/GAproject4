package com.Booth.Booth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This is to replace HttpStatus response message when resource are not found.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    // Spring boot will catch the excepting and use this.
    // When the Resource is not found it throws this back.
    public ResourceNotFoundException(String message){
        //Super constructor
        super(message);
    }
}
