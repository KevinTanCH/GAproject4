package com.Booth.Booth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException{

    // Parameter
    public ResourceNotFoundException(String message){
        //Super constructor
        super(message);
    }
}
