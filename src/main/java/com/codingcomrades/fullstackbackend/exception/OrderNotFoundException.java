package com.codingcomrades.fullstackbackend.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {

        super("Could not found the user with id" + id);
    }
}
