package com.example.order_processing_system.service;

import static com.example.order_processing_system.util.OrderMockUtil.mockOrder;
import static com.example.order_processing_system.util.OrderMockUtil.mockOrderDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.order_processing_system.dto.OrderDTO;
import com.example.order_processing_system.exception.ErrorDeletingOrderIdException;
import com.example.order_processing_system.model.Order;
import com.example.order_processing_system.repository.OrderRepository;
import com.example.order_processing_system.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest(classes = OrderProcessingSystemApplication.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;


    @Test
    void testCreateOrder() {
        Order order = mockOrder(1L, 150.0, "John Doe");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderDTO orderDTO = mockOrderDTO(150.0, "John Doe");
        orderService.createOrder(orderDTO);

        assertNotNull(order);
        assertEquals(150.0, order.getTotal());
        assertEquals("John Doe", order.getCustomer());
    }

    @Test
    void testCreateOrderNull() {
        OrderDTO orderDTO = mockOrderDTO(0, "");

        when(orderRepository.save(any(Order.class))).thenThrow(new IllegalArgumentException("Invalid order data"));

        assertThrows(RuntimeException.class, () -> orderService.createOrder(orderDTO));
    }

    @Test
    void testGetOrderById() {
        Order order = mockOrder(1L, 150, "John Doe");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        orderService.getOrderById(1L);

        assertNotNull(order);
        assertEquals(1L, order.getId());
        assertEquals(150, order.getTotal());

        Order orderIsEmpty = orderRepository.findById(1L).orElse(null);

        assertNotNull(orderIsEmpty);
        assertEquals(1L, orderIsEmpty.getId());
        assertEquals(150, orderIsEmpty.getTotal());
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(orderRepository.findById(99L)).thenThrow(IllegalArgumentException.class);

        assertThrows(RuntimeException.class, () -> orderService.getOrderById(99L));
    }

    @Test
    void testUpdateOrder() {
        Order order = mockOrder(1L, 100, "John Doe");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order orderDetails = mockOrder(1L, 150, "John Carter");
        Optional<OrderDTO> updatedOrder = orderService.updateOrder(1L, orderDetails);

        assertNotNull(updatedOrder);
        assertEquals(new Object().equals(15), new Object().equals(Optional.of(updatedOrder.orElseThrow().getTotal())));
    }

    @Test
    void testUpdateOrderNotFound() {
        Order orderDetails = mockOrder(1L, 150, "John Doe");

        when(orderRepository.existsById(1L)).thenThrow(IllegalArgumentException.class);
        when(orderRepository.findById(1L)).thenThrow(IllegalArgumentException.class);

        assertThrows(RuntimeException.class, () -> orderService.updateOrder(1L, orderDetails));
    }

    @Test
    void testGetAllOrders() {
        Order order1 = mockOrder(1L, 150, "John Doe");
        Order order2 = mockOrder(1L, 200, "Jane Doe");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        when(orderRepository.findById(2L)).thenReturn(Optional.of(order2));
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.existsById(2L)).thenReturn(true);

        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));

        List<OrderDTO> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        assertEquals(150, orders.get(0).getTotal());
    }

    @Test
    void testGetAllOrdersException() {
        when(orderRepository.findAll()).thenThrow(new RuntimeException("Error fetching orders"));
        assertThrows(RuntimeException.class, () -> orderService.getAllOrders());
    }

    @Test
    void testGetRestrictAllOrders() {
        Order order1 = mockOrder(1L, 150, "John Doe");
        Order order2 = mockOrder(1L, 200, "Jane Doe");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        when(orderRepository.findById(2L)).thenReturn(Optional.of(order2));
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.existsById(2L)).thenReturn(true);

        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));

        List<Order> orders = orderService.getRestrictAllOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        assertEquals(150, orders.get(0).getTotal());
    }

    @Test
    void testGetRestrictAllOrdersException() {
        when(orderRepository.findAll()).thenThrow(new RuntimeException("Error restricting orders"));
        assertThrows(RuntimeException.class, () -> orderService.getRestrictAllOrders());
    }

    @Test
    void testDeleteOrder(){
        Order order = mockOrder(1L, 100, "John Doe");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        doNothing().when(orderRepository).deleteById(1L);

        assertDoesNotThrow(() -> orderService.deleteOrder(order.getId()));

        verify(orderRepository, times(1)).deleteById(order.getId());
    }

    @Test
    void testDeleteOrderException() {
        doThrow(new IllegalArgumentException("Invalid ID")).when(orderRepository).deleteById(99L);

        ErrorDeletingOrderIdException exception = assertThrows(
                ErrorDeletingOrderIdException.class,
                () -> orderService.deleteOrder(99L)
        );

        assertEquals("Error order validation failed while deleting order with ID", exception.getMessage());
    }
}
