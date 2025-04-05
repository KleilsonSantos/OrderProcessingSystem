package com.example.order_processing_system.application;

import com.example.order_processing_system.OrderProcessingSystemApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ComponentScan(basePackages = "com.example.order_processing_system")
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderProcessingSystemApplicationTest {

    @Test
    void applicationStartsSuccessfully() {
        assertDoesNotThrow(() -> OrderProcessingSystemApplication.main(new String[]{}));
    }
}
