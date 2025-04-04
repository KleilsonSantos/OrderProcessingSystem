package com.example.order_processing_system.exception;

public class ErrorCreatingOrderException extends RuntimeException {
    public ErrorCreatingOrderException(String message, Exception e) {
        super(message, e);
    }

    public ErrorCreatingOrderException(String message) {
        super(message);
    }
}
