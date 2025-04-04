package com.example.order_processing_system.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GlobalExceptionHandler.class)
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        globalExceptionHandler = new GlobalExceptionHandler();
    }


    @Test
    void handleOrderNotFoundException() {
        ErrorOrderNotFoundException exception = new ErrorOrderNotFoundException("Order not found");

        ResponseEntity<String> response = globalExceptionHandler.handleOrderNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Order not found", response.getBody());
    }

    @Test
    void handleOrderNotFoundExceptionWithMessage() {
        ErrorOrderNotFoundException exception = new ErrorOrderNotFoundException("Order not found", new Exception("Order not found exception"));

        ResponseEntity<String> response = globalExceptionHandler.handleOrderNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Order not found", response.getBody());
    }

    @Test
    void handleOrderNotFoundExceptionWithId() {
        ErrorOrderNotFoundException exception = new ErrorOrderNotFoundException(1L);

        ResponseEntity<String> response = globalExceptionHandler.handleOrderNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Order not found with id: 1", response.getBody());
    }

    @Test
    void handleInvalidOrderException() {
        ErrorInvalidOrderException exception = new ErrorInvalidOrderException("Invalid order");

        ResponseEntity<String> response = globalExceptionHandler.handleInvalidOrderException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid order", response.getBody());
    }

    @Test
    void handleInvalidOrderExceptionWithMessage() {
        ErrorInvalidOrderException exception = new ErrorInvalidOrderException("Invalid order", new Exception("Invalid order exception"));

        ResponseEntity<String> response = globalExceptionHandler.handleInvalidOrderException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid order", response.getBody());
    }

    @Test
    void handleInvalidOrderDtoException() {
        ErrorInvalidOrderDtoException exception = new ErrorInvalidOrderDtoException("Invalid order DTO");

        ResponseEntity<String> response = globalExceptionHandler.handleInvalidOrderDtoException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid order DTO", response.getBody());
    }

    @Test
    void handleInvalidOrderDtoExceptionWithMessage() {
        ErrorInvalidOrderDtoException exception = new ErrorInvalidOrderDtoException("Invalid order DTO", new Exception("Invalid order DTO exception"));

        ResponseEntity<String> response = globalExceptionHandler.handleInvalidOrderDtoException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid order DTO", response.getBody());
    }

    @Test
    void handleNullPointerExceptionWithMessage() {
        NullPointerException exception = new NullPointerException("Null pointer exception");

        ResponseEntity<String> response = globalExceptionHandler.handleNullPointerException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Null value encountered: Null pointer exception", response.getBody());
    }

    @Test
    void handleIllegalArgumentException() {
        IllegalArgumentException exception = mock(IllegalArgumentException.class);

        when(exception.getMessage()).thenReturn("Illegal argument");

        ResponseEntity<String> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Illegal argument", response.getBody());
    }

    @Test
    void handleNullPointerException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        NullPointerException exception = new NullPointerException("Null value encountered");

        //lenient().when(request.getDescription(false)).thenReturn("Request description");

        ResponseEntity<String> response = globalExceptionHandler.handleNullPointerException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Null value encountered: Null value encountered", response.getBody());
    }



    @Test
    void handleGlobalException() {
        Exception exception = mock(Exception.class);
        when(exception.getMessage()).thenReturn("Global exception");

        ResponseEntity<String> response = globalExceptionHandler.handleGlobalException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Global exception", response.getBody());
    }

    @Test
    void errorFetchingOrdersException() {
        ErrorFetchingOrdersException exception = new ErrorFetchingOrdersException("Error fetching orders");

        ResponseEntity<String> response = globalExceptionHandler.handleErrorFetchingOrdersException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error fetching orders", response.getBody());
    }

    @Test
    void errorRestrictingOrdersException() {
        ErrorRestrictingOrdersException exception = new ErrorRestrictingOrdersException("Error restricting orders");

        ResponseEntity<String> response = globalExceptionHandler.handleErrorRestrictingOrdersException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error restricting orders", response.getBody());
    }

    @Test
    void errorCreatingOrderException() {
        ErrorCreatingOrderException exception = new ErrorCreatingOrderException("Error creating order");

        ResponseEntity<String> response = globalExceptionHandler.handleErrorCreatingOrderException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error creating order", response.getBody());
    }

    @Test
    void errorUpdatingOrderException() {
        ErrorUpdatingOrderException exception = new ErrorUpdatingOrderException("Error updating order");

        ResponseEntity<String> response = globalExceptionHandler.handleErrorUpdatingOrderException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error updating order", response.getBody());
    }

    @Test
    void errorDeletingOrderException() {
        ErrorDeletingOrderIdException exception = new ErrorDeletingOrderIdException("Error deleting order");

        ResponseEntity<String> response = globalExceptionHandler.handleErrorDeletingOrderException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error deleting order", response.getBody());
    }

    @Test
    void errorFetchingOrderByIdException() {
        ErrorFetchingOrderByIdException exception = new ErrorFetchingOrderByIdException("Error fetching order by ID");

        ResponseEntity<String> response = globalExceptionHandler.handleErrorFetchingOrderByIdException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error fetching order by ID", response.getBody());
    }

    @Test
    void errorFetchingOrderByIdExceptionWithMessage() {
        ErrorFetchingOrderByIdException exception = new ErrorFetchingOrderByIdException("Error fetching order by ID", new Exception("Error fetching order by ID exception"));

        ResponseEntity<String> response = globalExceptionHandler.handleErrorFetchingOrderByIdException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error fetching order by ID", response.getBody());
    }
}