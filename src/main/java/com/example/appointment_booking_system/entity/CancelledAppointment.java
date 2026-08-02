package com.example.appointment_booking_system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cancelled_appointments")
public class CancelledAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long originalAppointmentId;
    private String patientName;
    private String doctorName;
    private String doctorEmail;

    private LocalDate appointmentDate;
    private LocalTime startTime;

    @Column(length = 500)
    private String cancellationReason;

    private LocalDate cancelledOn;

    public CancelledAppointment() {
    }

    public CancelledAppointment(Long id, Long originalAppointmentId, String patientName, String doctorName, String doctorEmail, LocalDate appointmentDate, LocalTime startTime, String cancellationReason, LocalDate cancelledOn) {
        this.id = id;
        this.originalAppointmentId = originalAppointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.doctorEmail = doctorEmail;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.cancellationReason = cancellationReason;
        this.cancelledOn = cancelledOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOriginalAppointmentId() {
        return originalAppointmentId;
    }

    public void setOriginalAppointmentId(Long originalAppointmentId) {
        this.originalAppointmentId = originalAppointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
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

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public LocalDate getCancelledOn() {
        return cancelledOn;
    }

    public void setCancelledOn(LocalDate cancelledOn) {
        this.cancelledOn = cancelledOn;
    }

    public static CancelledAppointmentBuilder builder() {
        return new CancelledAppointmentBuilder();
    }

    public static class CancelledAppointmentBuilder {
        private Long id;
        private Long originalAppointmentId;
        private String patientName;
        private String doctorName;
        private String doctorEmail;
        private LocalDate appointmentDate;
        private LocalTime startTime;
        private String cancellationReason;
        private LocalDate cancelledOn;

        public CancelledAppointmentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CancelledAppointmentBuilder originalAppointmentId(Long originalAppointmentId) {
            this.originalAppointmentId = originalAppointmentId;
            return this;
        }

        public CancelledAppointmentBuilder patientName(String patientName) {
            this.patientName = patientName;
            return this;
        }

        public CancelledAppointmentBuilder doctorName(String doctorName) {
            this.doctorName = doctorName;
            return this;
        }

        public CancelledAppointmentBuilder doctorEmail(String doctorEmail) {
            this.doctorEmail = doctorEmail;
            return this;
        }

        public CancelledAppointmentBuilder appointmentDate(LocalDate appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }

        public CancelledAppointmentBuilder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public CancelledAppointmentBuilder cancellationReason(String cancellationReason) {
            this.cancellationReason = cancellationReason;
            return this;
        }

        public CancelledAppointmentBuilder cancelledOn(LocalDate cancelledOn) {
            this.cancelledOn = cancelledOn;
            return this;
        }

        public CancelledAppointment build() {
            return new CancelledAppointment(id, originalAppointmentId, patientName, doctorName, doctorEmail, appointmentDate, startTime, cancellationReason, cancelledOn);
        }
    }
}