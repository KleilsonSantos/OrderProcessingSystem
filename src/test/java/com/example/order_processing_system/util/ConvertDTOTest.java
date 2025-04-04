package com.example.order_processing_system.util;

import com.example.order_processing_system.OrderProcessingSystemApplication;
import com.example.order_processing_system.dto.OrderDTO;
import com.example.order_processing_system.exception.ErrorInvalidOrderException;
import com.example.order_processing_system.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.order_processing_system.util.OrderMockUtil.mockOrder;
import static com.example.order_processing_system.util.OrderMockUtil.mockOrderDTO;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OrderProcessingSystemApplication.class)
class ConvertDTOTest {

    @Test
    void convertToDTO_ValidOrder() {
        Order order = mockOrder(1L, 200.0,"Jhon Doe");

        OrderDTO orderDTO = ConvertDTO.convertToDTO(order);

        assertNotNull(orderDTO);
        assertEquals("Jhon Doe", orderDTO.getCustomer());
        assertEquals(200.0, orderDTO.getTotal());
    }

    @Test
    void convertToDTO_NullOrder() {
        assertThrows(ErrorInvalidOrderException.class, () -> ConvertDTO.convertToDTO(null));
    }

    @Test
    void convertToEntity_ValidOrderDTO() {
        OrderDTO orderDTO = mockOrderDTO(200.0,"Maria Julia");

        Order order = ConvertDTO.convertToEntity(orderDTO);

        assertNotNull(order);
        assertEquals("Maria Julia", order.getCustomer());
        assertEquals(200.0, order.getTotal());
    }

    @Test
    void convertToEntity_NullOrderDTO() {
        assertThrows(ErrorInvalidOrderException.class, () -> ConvertDTO.convertToEntity(null));
    }
}