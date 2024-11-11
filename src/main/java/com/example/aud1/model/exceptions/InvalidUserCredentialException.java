package com.example.aud1.model.exceptions;

public class InvalidUserCredentialException extends RuntimeException{

    public InvalidUserCredentialException() {
        super("Invalid user credentials exception");
    }
}
