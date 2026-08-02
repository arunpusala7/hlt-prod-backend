package com.example.appointment_booking_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RescheduleSlotsDTO {

    private Long doctorId;
    private LocalDate date;
    private List<Slot> slots;

    public RescheduleSlotsDTO() {
    }

    public RescheduleSlotsDTO(Long doctorId, LocalDate date, List<Slot> slots) {
        this.doctorId = doctorId;
        this.date = date;
        this.slots = slots;
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

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public static class Slot {
        private LocalTime startTime;
        private LocalTime endTime;

        public Slot() {
        }

        public Slot(LocalTime startTime, LocalTime endTime) {
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
