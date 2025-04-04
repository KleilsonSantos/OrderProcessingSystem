package com.example.order_processing_system.service;

import com.example.order_processing_system.dto.OrderDTO;

@SuppressWarnings("unused")
public interface ValidateOrderDto {
    void validateOrderDto(OrderDTO orderDTO) throws IllegalArgumentException;
}
