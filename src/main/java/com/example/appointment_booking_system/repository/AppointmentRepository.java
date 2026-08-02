package com.example.appointment_booking_system.repository;

import com.example.appointment_booking_system.entity.Appointment;
import com.example.appointment_booking_system.entity.AppointmentStatus;
import com.example.appointment_booking_system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorEmailAndAppointmentDate(String email, LocalDate date);

    // 1. Old Check (Checks everything)
    boolean existsByDoctorAndAppointmentDateAndStartTime(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime startTime
    );

    // ✅ 2. NEW METHOD (Required for your Service Update)
    // This tells Spring Data: "Check if a slot exists, but IGNORE rows with this specific status"
    boolean existsByDoctorAndAppointmentDateAndStartTimeAndStatusNot(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime startTime,
            AppointmentStatus status
    );

    List<Appointment> findByDoctorIdAndAppointmentDate(
            Long doctorId,
            LocalDate appointmentDate
    );

    List<Appointment> findByDoctorId(Long doctorId);
    void deleteByDoctorId(Long doctorId);
    void deleteByUserId(Long userId);

    // 🚨 3. CRITICAL FIX: Use LEFT JOIN FETCH to allow null-safe joins
    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor LEFT JOIN FETCH a.user WHERE a.user.id = :userId")
    List<Appointment> findByUserId(@Param("userId") Long userId);

    // 🚨 4. CRITICAL FIX: Use LEFT JOIN FETCH
    @Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor LEFT JOIN FETCH a.user WHERE a.ticketId = :ticketId")
    Optional<Appointment> findByTicketId(@Param("ticketId") String ticketId);


    // Find history of a specific patient for a specific doctor
    List<Appointment> findByUserIdAndDoctorIdAndStatusOrderByAppointmentDateDesc(
            Long userId,
            Long doctorId,
            AppointmentStatus status
    );

    List<Appointment> findByUserIdAndDoctorIdAndStatus(Long userId, Long doctorId, AppointmentStatus status);
}