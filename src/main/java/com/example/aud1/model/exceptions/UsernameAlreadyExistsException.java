package com.example.aud1.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String unsername) {
        super(String.format("User with username %s already exists", unsername));


    }
}
