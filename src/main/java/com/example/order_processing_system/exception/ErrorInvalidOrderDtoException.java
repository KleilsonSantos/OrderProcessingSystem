package com.example.order_processing_system.exception;


public class ErrorInvalidOrderDtoException extends RuntimeException {
    public ErrorInvalidOrderDtoException(String message) {
        super(message);
    }

    public ErrorInvalidOrderDtoException(String message, Exception e) {
        super(message, e);
    }
}