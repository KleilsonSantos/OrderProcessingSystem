package com.example.order_processing_system.service.impl;

import com.example.order_processing_system.dto.OrderDTO;
import com.example.order_processing_system.exception.*;
import com.example.order_processing_system.model.Order;
import com.example.order_processing_system.repository.OrderRepository;
import com.example.order_processing_system.service.OrderService;
import com.example.order_processing_system.util.ConvertDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        try {
            List<Order> orders = orderRepository.findAll();
            return orders.stream().map(ConvertDTO::convertToDTO).toList();
        } catch (Exception e) {
            throw new ErrorFetchingOrdersException("Error fetching orders", e);
        }
    }

    @Override
    public List<Order> getRestrictAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new ErrorRestrictingOrdersException("Error restricting orders", e);
        }
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        try {
            return orderRepository.save(ConvertDTO.convertToEntity(orderDTO));
        } catch (IllegalArgumentException e) {
            throw new ErrorCreatingOrderException("Error creating order", e);
        }
    }

    @Override
    public Optional<OrderDTO> getOrderById(Long id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            OrderDTO orderDTO = ConvertDTO.convertToDTO(order.orElseThrow(() -> new ErrorOrderNotFoundException(id)));
            return Optional.of(orderDTO);
        } catch (IllegalArgumentException e) {
            throw new ErrorDeletingOrderIdException("Error fetching order by ID", e);
        }
    }

    @Override
    public Optional<OrderDTO> updateOrder(Long id, Order order) {
        try {
            orderRepository.existsById(id);
            Order updatedOrder = orderRepository.save(order);
            OrderDTO orderDTO = ConvertDTO.convertToDTO(updatedOrder);
            return Optional.of(orderDTO);
        } catch (IllegalArgumentException e) {
            throw new ErrorUpdatingOrderException("Error updating order", e);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new ErrorDeletingOrderIdException("Error order validation failed while deleting order with ID", e);
        }
    }
}