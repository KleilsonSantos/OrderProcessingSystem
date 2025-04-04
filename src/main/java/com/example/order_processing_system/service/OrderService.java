package com.example.order_processing_system.service;

import com.example.order_processing_system.dto.OrderDTO;
import com.example.order_processing_system.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    List<Order> getRestrictAllOrders();
    Order createOrder(OrderDTO orderDTO);
    Optional<OrderDTO> getOrderById(Long id);
    Optional<OrderDTO> updateOrder(Long id, Order order);
    void deleteOrder(Long id);
}
