package com.example.aud1.model.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super(String.format("Product with id: %d was not found", id));
    }
}
