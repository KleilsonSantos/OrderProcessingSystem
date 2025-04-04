package com.example.order_processing_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "com.example.order_processing_system.model")
public class OrderProcessingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderProcessingSystemApplication.class, args);
	}
}
