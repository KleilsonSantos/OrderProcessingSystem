package com.example.order_processing_system.exception;

public class ErrorUpdatingOrderException extends RuntimeException {
    public ErrorUpdatingOrderException(String message, Exception e) {
        super(message, e);
    }

    public ErrorUpdatingOrderException(String message) {
        super(message);
    }
}
