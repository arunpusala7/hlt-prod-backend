package com.example.appointment_booking_system.service;

import com.example.appointment_booking_system.dto.AppointmentResponseDTO;
import com.example.appointment_booking_system.dto.CreateDoctorAccountDTO;
import com.example.appointment_booking_system.entity.*;
import com.example.appointment_booking_system.exception.ResourceNotFoundException;
import com.example.appointment_booking_system.repository.AppointmentRepository;
import com.example.appointment_booking_system.repository.DoctorRepository;
import com.example.appointment_booking_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final com.example.appointment_booking_system.repository.AvailabilityRepository availabilityRepository;
    private final com.example.appointment_booking_system.repository.PaymentRepository paymentRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AdminServiceImpl(AppointmentRepository appointmentRepository,
                            DoctorRepository doctorRepository,
                            UserRepository userRepository,
                            com.example.appointment_booking_system.repository.AvailabilityRepository availabilityRepository,
                            com.example.appointment_booking_system.repository.PaymentRepository paymentRepository,
                            PasswordEncoder passwordEncoder,
                            EmailService emailService) {

        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.availabilityRepository = availabilityRepository;
        this.paymentRepository = paymentRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    // ===============================
    // GET ALL APPOINTMENTS
    // ===============================
    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ===============================
    // GET APPOINTMENTS BY DOCTOR
    // ===============================
    @Override
    public List<AppointmentResponseDTO> getAppointmentsByDoctor(Long doctorId) {

        doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        return appointmentRepository.findByDoctorId(doctorId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ===============================
    // CANCEL APPOINTMENT (ADMIN)
    // ===============================
    @Override
    public void cancelAppointmentByAdmin(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);

        if (appointment.getUser() != null && appointment.getUser().getEmail() != null) {
            emailService.sendCancellationEmail(
                    appointment.getUser().getEmail(),
                    appointment.getUser().getName(),
                    appointment.getDoctor() != null ? appointment.getDoctor().getName() : "Doctor",
                    appointment.getAppointmentDate() != null ? appointment.getAppointmentDate().toString() : "",
                    appointment.getStartTime() != null ? appointment.getStartTime().toString() : "",
                    "Cancelled by Admin",
                    appointment.getTicketId()
            );
        }
    }

    // ===============================
    // 🆕 CREATE DOCTOR LOGIN ACCOUNT
    // ===============================
    @Override
    public void createDoctorAccount(CreateDoctorAccountDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Doctor account already exists");
        }

        // ===============================
        // 1️⃣ CREATE USER (LOGIN)
        // ===============================
        User doctorUser = new User();
        doctorUser.setName(dto.getName());
        doctorUser.setEmail(dto.getEmail());
        doctorUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        doctorUser.setRole(Role.DOCTOR);

        userRepository.save(doctorUser);

        // ===============================
        // 2️⃣ CREATE DOCTOR (DOMAIN ENTITY)
        // ===============================
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialization(dto.getSpecialization()); // ✅ THIS WAS MISSING

        doctorRepository.save(doctor);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void deleteDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        // 1. Delete all payments linked to doctor's appointments
        List<Appointment> doctorAppointments = appointmentRepository.findByDoctorId(doctorId);
        for (Appointment appt : doctorAppointments) {
            paymentRepository.deleteByAppointmentId(appt.getId());
        }

        // 2. Delete all appointments for this doctor
        appointmentRepository.deleteByDoctorId(doctorId);

        // 3. Delete all availability slots for this doctor
        availabilityRepository.deleteByDoctorId(doctorId);

        // 4. Delete linked User login account if present
        userRepository.findByEmail(doctor.getEmail()).ifPresent(userRepository::delete);

        // 5. Delete Doctor record
        doctorRepository.delete(doctor);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findByRole(Role.USER);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 1. Delete all payments linked to user's appointments
        List<Appointment> userAppointments = appointmentRepository.findByUserId(userId);
        for (Appointment appt : userAppointments) {
            paymentRepository.deleteByAppointmentId(appt.getId());
        }

        // 2. Delete all appointments for this user
        appointmentRepository.deleteByUserId(userId);

        // 3. Delete User record
        userRepository.delete(user);
    }



    // ===============================
    // MAPPER METHOD
    // ===============================
    private AppointmentResponseDTO mapToDTO(Appointment appointment) {

        return AppointmentResponseDTO.builder()
                .appointmentId(appointment.getId())
                .doctorName(appointment.getDoctor().getName())
                .userName(appointment.getUser().getName())
                .date(appointment.getAppointmentDate())
                .startTime(appointment.getStartTime())
                .endTime(appointment.getEndTime())
                .status(appointment.getStatus().name())
                .build();
    }
}
