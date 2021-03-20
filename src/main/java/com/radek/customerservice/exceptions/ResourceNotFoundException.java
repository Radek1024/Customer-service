package com.radek.customerservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id){
        super("Resource not found under id: " + id);
    }
    public ResourceNotFoundException(String message){
        super("Resource not found under id: " + message);
    }
}
