package com.example.order_processing_system.util;

import com.example.order_processing_system.dto.OrderDTO;
import com.example.order_processing_system.exception.ErrorInvalidOrderException;
import com.example.order_processing_system.model.Order;


public class ConvertDTO {
    private ConvertDTO() {
    }

    public static OrderDTO convertToDTO(Order order) {
        if (order == null) {
            throw new ErrorInvalidOrderException("Order cannot be null");
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(order.getCustomer());
        orderDTO.setTotal(order.getTotal());
        return orderDTO;
    }

    public static Order convertToEntity(OrderDTO dto) {
        if (dto == null) {
            throw new ErrorInvalidOrderException("Order cannot be null");
        }
        Order order = new Order();
        order.setCustomer(dto.getCustomer());
        order.setTotal(dto.getTotal());
        return order;
    }
}
