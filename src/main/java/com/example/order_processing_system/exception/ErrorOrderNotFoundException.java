package com.example.order_processing_system.exception;


public class ErrorOrderNotFoundException extends RuntimeException {
    public ErrorOrderNotFoundException(Long id) {
        super("Order not found with id: " + id);
    }
    public ErrorOrderNotFoundException(String message) {
        super(message);
    }

    public ErrorOrderNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
