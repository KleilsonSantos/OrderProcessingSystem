package com.example.order_processing_system.controller;

import com.example.order_processing_system.dto.OrderDTO;
import com.example.order_processing_system.exception.ErrorOrderNotFoundException;
import com.example.order_processing_system.model.Order;
import com.example.order_processing_system.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @SuppressWarnings("unused")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/admin")
    @SuppressWarnings("unused")
    public ResponseEntity<List<Order>> getRestrictAllOrders() {
        return ResponseEntity.ok(orderService.getRestrictAllOrders());
    }

    @PostMapping
    @SuppressWarnings("unused")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Order order = orderService.createOrder(orderDTO);
        return ResponseEntity.status(201).body(order);
    }

    @GetMapping("/{id}")
    @SuppressWarnings("unused")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.getOrderById(id).orElseThrow(() -> new ErrorOrderNotFoundException(id));
        return ResponseEntity.ok(orderDTO);
    }

    @PutMapping("/{id}")
    @SuppressWarnings("unused")
    public ResponseEntity<Optional<OrderDTO>> updateOrder(@PathVariable Long id, @Valid @RequestBody Order orderRequest) {
        Optional<OrderDTO>  optionalOrderDTO = orderService.updateOrder(id, orderRequest);
        return ResponseEntity.ok(optionalOrderDTO);
    }

    @DeleteMapping("/{id}")
    @SuppressWarnings("unused")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
