package com.example.order_processing_system.exception;

public class ErrorFetchingOrdersException extends RuntimeException {
    public ErrorFetchingOrdersException(String message, Exception e) {
        super(message, e);
    }

    public ErrorFetchingOrdersException(String message) {
        super(message);
    }
}
