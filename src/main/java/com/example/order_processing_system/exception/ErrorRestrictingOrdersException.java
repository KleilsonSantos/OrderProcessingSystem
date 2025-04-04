package com.example.order_processing_system.exception;

public class ErrorRestrictingOrdersException extends RuntimeException {
    public ErrorRestrictingOrdersException(String message, Exception e) {
        super(message, e);
    }

    public ErrorRestrictingOrdersException(String message) {
        super(message);
    }
}
