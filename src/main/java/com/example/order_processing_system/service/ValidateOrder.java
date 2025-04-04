package com.example.order_processing_system.service;

import com.example.order_processing_system.model.Order;

@SuppressWarnings("unused")
public interface ValidateOrder {
    void validateOrder(Order order) throws IllegalArgumentException;
}
