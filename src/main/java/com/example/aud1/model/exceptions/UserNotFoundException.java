package com.example.aud1.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super(String.format("User with id %s was not found", username));
    }
}
