package com.example.appointment_booking_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DoctorAvailabilityDTO {

    private Long doctorId;
    private LocalDate date;
    private List<TimeSlot> availableSlots;

    public DoctorAvailabilityDTO() {
    }

    public DoctorAvailabilityDTO(Long doctorId, LocalDate date, List<TimeSlot> availableSlots) {
        this.doctorId = doctorId;
        this.date = date;
        this.availableSlots = availableSlots;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<TimeSlot> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<TimeSlot> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public static DoctorAvailabilityDTOBuilder builder() {
        return new DoctorAvailabilityDTOBuilder();
    }

    public static class DoctorAvailabilityDTOBuilder {
        private Long doctorId;
        private LocalDate date;
        private List<TimeSlot> availableSlots;

        public DoctorAvailabilityDTOBuilder doctorId(Long doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public DoctorAvailabilityDTOBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public DoctorAvailabilityDTOBuilder availableSlots(List<TimeSlot> availableSlots) {
            this.availableSlots = availableSlots;
            return this;
        }

        public DoctorAvailabilityDTO build() {
            return new DoctorAvailabilityDTO(doctorId, date, availableSlots);
        }
    }

    public static class TimeSlot {
        private LocalTime startTime;
        private LocalTime endTime;

        public TimeSlot() {
        }

        public TimeSlot(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
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
    }
}
