package com.example.appointment_booking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private Long doctorId;
    private String appointmentDate;
    private String startTime;
    private String endTime;
}
