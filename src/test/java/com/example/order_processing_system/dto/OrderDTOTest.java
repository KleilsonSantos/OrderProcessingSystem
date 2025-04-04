package com.example.order_processing_system.dto;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.order_processing_system.util.OrderMockUtil.mockOrderDTO;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OrderDTO.class)
class OrderDTOTest {
    @Test
    void getCustomer() {
        OrderDTO orderDTO = mockOrderDTO(200.0, "Maria Julia");
        assertEquals("Maria Julia", orderDTO.getCustomer());
    }

    @Test
    void setCustomer() {
        OrderDTO orderDTO = mockOrderDTO(200.0, "Maria Julia");
        orderDTO.setCustomer("Carlos Doe");
        assertEquals("Carlos Doe", orderDTO.getCustomer());
    }

    @Test
    void getTotal() {
        OrderDTO orderDTO = mockOrderDTO(200.0, "Maria Julia");
        assertEquals(200.0, orderDTO.getTotal());
    }

    @Test
    void setTotal() {
        OrderDTO orderDTO = mockOrderDTO(200.0, "Maria Julia");
        orderDTO.setTotal(300.0);
        assertEquals(300.0, orderDTO.getTotal());
    }

    @Test
    void testToString() {
        OrderDTO orderDTO = mockOrderDTO(200.0, "Maria Julia");
        String expectedString = "OrderDTO(customer=Maria Julia, total=200.0)";
        assertEquals(expectedString, orderDTO.toString());
    }

    @Test
    void testOrderDTO_Constructor() {
        OrderDTO orderDTO = new OrderDTO("Maria Julia", 200.0);
        assertEquals(200.0, orderDTO.getTotal());
        assertEquals("Maria Julia", orderDTO.getCustomer());
    }

    @Test
    void testValidateOrderDTO_CUSTOMER_NULL() {
        OrderDTO order = new OrderDTO("John Doe", -100.0);
        try {
            if (order.getCustomer() == null) {
                fail("Customer name cannot be null");
            }
        } catch (AssertionFailedError assertionFailedError) {
            assertEquals("Customer name cannot be null", assertionFailedError.getMessage());
        }
    }

    @Test
    void testValidateOrderDTO_CUSTOMER_EMPTY() {
        OrderDTO order = new OrderDTO("John Doe", -100.0);
        try {
            if (order.getCustomer().isEmpty()) {
                fail("Customer name cannot be null");
            }
        } catch (AssertionFailedError assertionFailedError) {
            assertEquals("Customer name cannot be null", assertionFailedError.getMessage());
        }
    }


    @Test
    void testValidateOrder_TOTAL_NEGATIVE() {
        OrderDTO order = new OrderDTO("John Doe", -100.0);
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
        OrderDTO orderDTO = new OrderDTO("John Doe", 0.0);
        try {
            if (orderDTO.getTotal() <= 0) {
                fail("The value must be greater than zero.");
            }
        } catch (AssertionFailedError assertionFailedError) {
            assertEquals("The value must be greater than zero.", assertionFailedError.getMessage());
        }
    }
}