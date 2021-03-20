package com.radek.customerservice.exceptions;

public class ResourceAlreadyExists extends RuntimeException{
    public  ResourceAlreadyExists(String message){
        super("Resource already exists under email: " + message);
    }
}
