package com.example.appointment_booking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderResponse {
    private String orderId;
    private Double amount;
    private String currency;
    private String keyId;
    private String doctorName;
    private String specialization;
    private Double consultationFee;
}
