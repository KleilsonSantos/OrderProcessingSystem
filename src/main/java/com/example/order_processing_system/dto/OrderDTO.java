package com.example.order_processing_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @NotBlank(message = "Customer name cannot be null or empty")
    private String customer;

    @Min(value = 0, message = "Total amount must be greater than zero")
    private double total;
}