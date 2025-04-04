package com.example.order_processing_system.controller;

import static com.example.order_processing_system.util.OrderMockUtil.mockOrder;
import static com.example.order_processing_system.util.OrderMockUtil.mockOrderList;
import static org.hamcrest.Matchers.closeTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.order_processing_system.dto.OrderDTO;
import com.example.order_processing_system.model.Order;
import com.example.order_processing_system.service.OrderService;
import com.example.order_processing_system.util.ConvertDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    @SuppressWarnings("unused")
    private OrderService orderService;

    @Test
    void testGetOrders() throws Exception {
        List<Order> orders = mockOrderList();
        List<OrderDTO> orderDTOS = orders.stream().map(ConvertDTO::convertToDTO).toList();

        when(orderService.getAllOrders()).thenReturn(orderDTOS);

        mockMvc.perform(get("/orders"))
                .andExpectAll(
                        status().isOk(),
                        content()
                                .contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$[0].customer")
                                .value("John Doe"),
                        jsonPath("$[0].total")
                                .value(closeTo(150.0, 0.01)),
                        jsonPath("$[1].customer")
                                .value("Jane Doe"),
                        jsonPath("$[1].total")
                                .value(closeTo(200.0, 0.01)));
    }

    @Test
    void testGetRestrictOrders() throws Exception {
        List<Order> orders = mockOrderList();

        when(orderService.getRestrictAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/orders/admin"))
                .andExpectAll(
                        status().isOk(),
                        content()
                                .contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$[0].customer")
                                .value("John Doe"),
                        jsonPath("$[0].total")
                                .value(closeTo(150.0, 0.01)),
                        jsonPath("$[1].customer")
                                .value("Jane Doe"),
                        jsonPath("$[1].total")
                                .value(closeTo(200.0, 0.01)));
    }


    @Test
    void testCreateOrder() throws Exception {
        Order order = mockOrder(1L, 100.0, "John Doe");
        OrderDTO orderDTO = ConvertDTO.convertToDTO(order);
        when(orderService.createOrder(orderDTO)).thenReturn(order);

        String UTF_8 = "UTF-8";
        String orderJson = "{ \"customer\": \"John Doe\", \"total\": 100.0 }";

        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(orderJson))
                .andExpect(status().isCreated()).andExpect(content().encoding(UTF_8));
    }

    @Test
    void testGetOrderById() throws Exception {
        OrderDTO orderDTO = ConvertDTO.convertToDTO(mockOrder(2L, 200.0, "Jane Doe"));
        when(orderService.getOrderById(2L)).thenReturn(Optional.of(orderDTO));

        mockMvc.perform(get("/orders/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customer").value("Jane Doe"))
                .andExpect(jsonPath("$.total").value(200.0));
    }

    @Test
    void testUpdateOrder() throws Exception {
        Order existingOrder = mockOrder(1L, 100.0, "John Doe");
        Order updatedOrder = mockOrder(1L, 150.0, "Jane Doe");

        String updatedOrderJson = "{ \"customer\": \"Jane Doe\", \"total\": 150.0 }";

        when(orderService.getOrderById(1L)).thenReturn(Optional.of(ConvertDTO.convertToDTO(existingOrder)));
        when(orderService.updateOrder(eq(1L), any(Order.class))).thenReturn(Optional.of(ConvertDTO.convertToDTO(updatedOrder)));

        mockMvc.perform(put("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedOrderJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer").value("Jane Doe"))
                .andExpect(jsonPath("$.total").value(150.0));
    }

    @Test
    void testDeleteOrder() throws Exception {
        Order order = new Order(1L, "John Doe", 100.0);

        when(orderService.getOrderById(1L)).thenReturn(Optional.of(ConvertDTO.convertToDTO(order)));
        doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/orders/1"))
                .andExpect(status().isNoContent());
    }
}
