package com.uniforum.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String id){
        super("Could not found with id "+id);
    }
}
