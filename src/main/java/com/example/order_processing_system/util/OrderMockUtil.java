package com.example.order_processing_system.util;

import com.example.order_processing_system.dto.OrderDTO;
import com.example.order_processing_system.model.Order;

import java.util.List;

public class OrderMockUtil {
    private OrderMockUtil() {
    }

    public static Order mockOrder(Long id, double total, String customer) {
        Order order = new Order();
        order.setId(id);
        order.setTotal(total);
        order.setCustomer(customer);
        return order;
    }

    public static OrderDTO mockOrderDTO(double total, String customer) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotal(total);
        orderDTO.setCustomer(customer);
        return orderDTO;
    }

    public static List<Order> mockOrderList() {
        return List.of(
                mockOrder(1L, 150.0, "John Doe"),
                mockOrder(2L, 200.0, "Jane Doe")
        );
    }
}
