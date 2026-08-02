package com.example.appointment_booking_system.service;

import com.example.appointment_booking_system.dto.CreateOrderRequest;
import com.example.appointment_booking_system.dto.CreateOrderResponse;
import com.example.appointment_booking_system.dto.PaymentVerificationRequest;
import com.example.appointment_booking_system.entity.Appointment;
import com.example.appointment_booking_system.entity.Payment;
import java.util.List;

public interface PaymentService {
    CreateOrderResponse createOrder(CreateOrderRequest request);
    Appointment verifyAndCompletePayment(PaymentVerificationRequest request);
    Double getDoctorConsultationFee(Long doctorId);
    Double updateDoctorConsultationFee(Long doctorId, Double fee);
    List<Payment> getAllPayments();
}
