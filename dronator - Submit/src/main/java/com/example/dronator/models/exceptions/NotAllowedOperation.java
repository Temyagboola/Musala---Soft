package com.example.dronator.models.exceptions;

public class NotAllowedOperation extends RuntimeException {

    public NotAllowedOperation(String message) {
        super(message);
    }

}
