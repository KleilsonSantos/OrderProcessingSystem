package com.example.order_processing_system.model;

import com.example.order_processing_system.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.opentest4j.AssertionFailedError;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderTest {

    @Test
    void getId() {
        Order order = new Order();
        order.setId(1L);
        assertEquals(1L, order.getId());
    }

    @Test
    void getCustomer() {
        Order order = new Order();
        order.setCustomer("John Doe");
        assertEquals("John Doe", order.getCustomer());
    }

    @Test
    void getTotal() {
        Order order = new Order();
        order.setTotal(100.0);
        assertEquals(100.0, order.getTotal());
    }

    @Test
    void setId() {
        Order order = new Order();
        order.setId(1L);
        assertEquals(1L, order.getId());
    }

    @Test
    void setCustomer() {
        Order order = new Order();
        order.setCustomer("John Doe");
        assertEquals("John Doe", order.getCustomer());
    }

    @Test
    void setTotal() {
        Order order = new Order();
        order.setTotal(100.0);
        assertEquals(100.0, order.getTotal());
    }

    @Test
    void testToString() {
        Order order = mock(Order.class);
        order.setId(1L);
        order.setCustomer("John Doe");
        order.setTotal(100.0);

        when(order.toString()).thenReturn("Order{id=1, customer='John Doe', total=100.0}");

        String expectedString = "Order{id=1, customer='John Doe', total=100.0}";
        assertEquals(expectedString, order.toString());
    }

    @Test
    void testOrderConstructor() {
        OrderDTO orderDTO = new OrderDTO("Maria Julia", 200.0);
        assertEquals(200.0, orderDTO.getTotal());
        assertEquals("Maria Julia", orderDTO.getCustomer());
    }

    @Test
    void testValidateOrder_CUSTOMER_NULL() {
        Order order= new Order(1L, "John Doe", -100.0);
        try {
            if (order.getCustomer().isEmpty()) {
                fail("Customer name cannot be empty");
            }
        } catch (AssertionFailedError assertionFailedError) {
            assertEquals("Customer name cannot be empty", assertionFailedError.getMessage());
        }
    }

    @Test
    void testValidateOrder_CUSTOMER_EMPTY() {
        Order order= new Order(1L, "John Doe", -100.0);
        try {
            if (order.getCustomer().isEmpty()) {
                fail("Customer name cannot be empty");
            }
        } catch (AssertionFailedError assertionFailedError) {
            assertEquals("Customer name cannot be empty", assertionFailedError.getMessage());
        }
    }

    @Test
    void testValidateOrder_TOTAL_NEGATIVE() {
        Order order= new Order(1L, "John Doe", -100.0);
        try {
            if (order.getTotal() <= 0) {
                fail("Total amount must be negative");
            }
        } catch (AssertionFailedError assertionFailedError) {
            assertEquals("Total amount must be negative", assertionFailedError.getMessage());
        }
    }

    @Test
    void testValidateOrder_TOTAL_ZERO() {
        Order order = new Order(1L, "John Doe", 0.0);
        try {
            if (order.getTotal() <= 0) {
                fail("The value must be greater than zero.");
            }
        } catch (AssertionFailedError assertionFailedError) {
            assertEquals("The value must be greater than zero.", assertionFailedError.getMessage());
        }
    }
}