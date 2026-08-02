package com.example.appointment_booking_system.repository;

import com.example.appointment_booking_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);
    Optional<Payment> findByAppointmentId(Long appointmentId);
    void deleteByAppointmentId(Long appointmentId);
}
