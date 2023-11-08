package com.cookbook.domain.exception;
/*
This exception will be thrown at the service classes.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
