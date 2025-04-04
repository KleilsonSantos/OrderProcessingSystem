package com.example.order_processing_system.exception;

public class ErrorFetchingOrderByIdException extends RuntimeException {
    public ErrorFetchingOrderByIdException(String message, Exception e) {
        super(message, e);
    }

    public ErrorFetchingOrderByIdException(String message) {
        super(message);
    }
}
