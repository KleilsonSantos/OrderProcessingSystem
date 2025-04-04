package com.example.order_processing_system.exception;


public class ErrorInvalidOrderException extends RuntimeException {
    public ErrorInvalidOrderException(String message) {
        super(message);
    }

    public ErrorInvalidOrderException(String message, Exception e) {
        super(message, e);
    }
}