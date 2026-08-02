package com.example.appointment_booking_system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "appointments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "doctor_id", "appointment_date", "start_time"
                })
        }
)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Column(unique = true)
    private String ticketId;

    @Column(length = 1000)
    private String prescription;

    public Appointment() {
    }

    public Appointment(Long id, User user, Doctor doctor, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, AppointmentStatus status, String ticketId, String prescription) {
        this.id = id;
        this.user = user;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.ticketId = ticketId;
        this.prescription = prescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public static AppointmentBuilder builder() {
        return new AppointmentBuilder();
    }

    public static class AppointmentBuilder {
        private Long id;
        private User user;
        private Doctor doctor;
        private LocalDate appointmentDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private AppointmentStatus status;
        private String ticketId;
        private String prescription;

        public AppointmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AppointmentBuilder user(User user) {
            this.user = user;
            return this;
        }

        public AppointmentBuilder doctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public AppointmentBuilder appointmentDate(LocalDate appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }

        public AppointmentBuilder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public AppointmentBuilder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public AppointmentBuilder status(AppointmentStatus status) {
            this.status = status;
            return this;
        }

        public AppointmentBuilder ticketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public AppointmentBuilder prescription(String prescription) {
            this.prescription = prescription;
            return this;
        }

        public Appointment build() {
            return new Appointment(id, user, doctor, appointmentDate, startTime, endTime, status, ticketId, prescription);
        }
    }
}
