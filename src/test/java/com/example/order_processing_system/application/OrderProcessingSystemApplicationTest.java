package com.example.order_processing_system.application;

import com.example.order_processing_system.OrderProcessingSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(
        classes = OrderProcessingSystemApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {
                "spring.main.allow-bean-definition-overriding=true",
                "spring.main.web-application-type=none"
        }
)
@ComponentScan(basePackages = "com.example.order_processing_system")
class OrderProcessingSystemApplicationTest {

    @Test
    void applicationStartsSuccessfully() {
        assertDoesNotThrow(() -> OrderProcessingSystemApplication.main(new String[]{}));
    }
}
