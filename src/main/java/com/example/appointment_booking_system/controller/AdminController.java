package com.example.appointment_booking_system.controller;

//package com.example.appointment.controller;


import com.example.appointment_booking_system.dto.AppointmentResponseDTO;
import com.example.appointment_booking_system.dto.CreateDoctorAccountDTO;
import com.example.appointment_booking_system.entity.Doctor;
import com.example.appointment_booking_system.service.AdminService;
import com.example.appointment_booking_system.service.PaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final PaymentService paymentService;

    public AdminController(AdminService adminService, PaymentService paymentService) {
        this.adminService = adminService;
        this.paymentService = paymentService;
    }

    // ===============================
    // GET ALL PAYMENTS
    // ===============================
    @GetMapping("/payments")
    public List<com.example.appointment_booking_system.entity.Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // ===============================
    // GET ALL APPOINTMENTS
    // ===============================
    @GetMapping("/appointments")
    public List<AppointmentResponseDTO> getAllAppointments() {
        return adminService.getAllAppointments();
    }

    // ===============================
    // GET APPOINTMENTS BY DOCTOR
    // ===============================
    @GetMapping("/appointments/doctor/{doctorId}")
    public List<AppointmentResponseDTO> getAppointmentsByDoctor(
            @PathVariable Long doctorId) {

        return adminService.getAppointmentsByDoctor(doctorId);
    }

    // ===============================
    // CANCEL ANY APPOINTMENT
    // ===============================
    @DeleteMapping("/appointments/{appointmentId}")
    public String cancelAppointmentByAdmin(
            @PathVariable Long appointmentId) {

        adminService.cancelAppointmentByAdmin(appointmentId);
        return "Appointment cancelled by admin";
    }

    @PostMapping("/create-doctor-account")
    public String createDoctorAccount(
            @RequestBody CreateDoctorAccountDTO dto) {

        adminService.createDoctorAccount(dto);
        return "Doctor account created successfully";
    }

    // ===============================
    // DELETE DOCTOR
    // ===============================
    @DeleteMapping("/doctors/{doctorId}")
    public String deleteDoctor(@PathVariable Long doctorId) {
        adminService.deleteDoctor(doctorId);
        return "Doctor account deleted successfully";
    }

    // ===============================
    // GET ALL USERS (PATIENTS)
    // ===============================
    @GetMapping("/users")
    public List<com.example.appointment_booking_system.entity.User> getAllUsers() {
        return adminService.getAllUsers();
    }

    // ===============================
    // DELETE USER (PATIENT)
    // ===============================
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return "User account deleted successfully";
    }

}

