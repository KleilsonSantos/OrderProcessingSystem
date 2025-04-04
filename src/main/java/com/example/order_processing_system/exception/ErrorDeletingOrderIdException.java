package com.example.order_processing_system.exception;

public class ErrorDeletingOrderIdException extends RuntimeException{
    public ErrorDeletingOrderIdException(String message, Exception e) {
        super(message, e);
    }

    public ErrorDeletingOrderIdException(String message) {
        super(message);
    }
}
