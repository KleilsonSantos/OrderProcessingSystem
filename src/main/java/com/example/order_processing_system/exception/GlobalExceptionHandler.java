package com.example.order_processing_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorOrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(ErrorOrderNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorInvalidOrderException.class)
    public ResponseEntity<String> handleInvalidOrderException(ErrorInvalidOrderException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorInvalidOrderDtoException.class)
    public ResponseEntity<String> handleInvalidOrderDtoException(ErrorInvalidOrderDtoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("Null value encountered: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorFetchingOrdersException.class)
    public ResponseEntity<String> handleErrorFetchingOrdersException(ErrorFetchingOrdersException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorRestrictingOrdersException.class)
    public ResponseEntity<String> handleErrorRestrictingOrdersException(ErrorRestrictingOrdersException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorCreatingOrderException.class)
    public ResponseEntity<String> handleErrorCreatingOrderException(ErrorCreatingOrderException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorUpdatingOrderException.class)
    public ResponseEntity<String> handleErrorUpdatingOrderException(ErrorUpdatingOrderException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorDeletingOrderIdException.class)
    public ResponseEntity<String> handleErrorDeletingOrderException(ErrorDeletingOrderIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorFetchingOrderByIdException.class)
    public ResponseEntity<String> handleErrorFetchingOrderByIdException(ErrorFetchingOrderByIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
