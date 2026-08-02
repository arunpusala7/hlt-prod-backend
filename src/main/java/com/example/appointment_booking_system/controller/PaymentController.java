package com.example.appointment_booking_system.controller;

import com.example.appointment_booking_system.dto.CreateOrderRequest;
import com.example.appointment_booking_system.dto.CreateOrderResponse;
import com.example.appointment_booking_system.dto.PaymentVerificationRequest;
import com.example.appointment_booking_system.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        CreateOrderResponse response = paymentService.createOrder(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-payment")
    public ResponseEntity<?> verifyPayment(@RequestBody PaymentVerificationRequest request) {
        paymentService.verifyAndCompletePayment(request);
        return ResponseEntity.ok(Map.of("message", "Payment verified and appointment booked successfully!", "status", "SUCCESS"));
    }

    @GetMapping("/doctor/{doctorId}/fee")
    public ResponseEntity<Map<String, Double>> getDoctorFee(@PathVariable Long doctorId) {
        Double fee = paymentService.getDoctorConsultationFee(doctorId);
        return ResponseEntity.ok(Map.of("consultationFee", fee));
    }

    @PutMapping("/doctor/{doctorId}/fee")
    public ResponseEntity<Map<String, Object>> updateDoctorFee(@PathVariable Long doctorId, @RequestBody Map<String, Double> body) {
        Double newFee = body.get("consultationFee");
        if (newFee == null || newFee < 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid consultation fee"));
        }
        Double updatedFee = paymentService.updateDoctorConsultationFee(doctorId, newFee);
        return ResponseEntity.ok(Map.of("message", "Consultation fee updated successfully", "consultationFee", updatedFee));
    }
}
