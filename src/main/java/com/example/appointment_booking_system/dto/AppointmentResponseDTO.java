package com.example.appointment_booking_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentResponseDTO {

    private Long appointmentId;
    private Long doctorId;
    private String userName;
    private String doctorName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
    private String ticketId;
    private String prescription;
    private String doctorSpecialization;
    private Long userId;

    public AppointmentResponseDTO() {
    }

    public AppointmentResponseDTO(Long appointmentId, Long doctorId, String userName, String doctorName, LocalDate date, LocalTime startTime, LocalTime endTime, String status, String ticketId, String prescription, String doctorSpecialization, Long userId) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.userName = userName;
        this.doctorName = doctorName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.ticketId = ticketId;
        this.prescription = prescription;
        this.doctorSpecialization = doctorSpecialization;
        this.userId = userId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static AppointmentResponseDTOBuilder builder() {
        return new AppointmentResponseDTOBuilder();
    }

    public static class AppointmentResponseDTOBuilder {
        private Long appointmentId;
        private Long doctorId;
        private String userName;
        private String doctorName;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;
        private String status;
        private String ticketId;
        private String prescription;
        private String doctorSpecialization;
        private Long userId;

        public AppointmentResponseDTOBuilder appointmentId(Long appointmentId) {
            this.appointmentId = appointmentId;
            return this;
        }

        public AppointmentResponseDTOBuilder doctorId(Long doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public AppointmentResponseDTOBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public AppointmentResponseDTOBuilder doctorName(String doctorName) {
            this.doctorName = doctorName;
            return this;
        }

        public AppointmentResponseDTOBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public AppointmentResponseDTOBuilder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public AppointmentResponseDTOBuilder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public AppointmentResponseDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public AppointmentResponseDTOBuilder ticketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public AppointmentResponseDTOBuilder prescription(String prescription) {
            this.prescription = prescription;
            return this;
        }

        public AppointmentResponseDTOBuilder doctorSpecialization(String doctorSpecialization) {
            this.doctorSpecialization = doctorSpecialization;
            return this;
        }

        public AppointmentResponseDTOBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public AppointmentResponseDTO build() {
            return new AppointmentResponseDTO(appointmentId, doctorId, userName, doctorName, date, startTime, endTime, status, ticketId, prescription, doctorSpecialization, userId);
        }
    }
}