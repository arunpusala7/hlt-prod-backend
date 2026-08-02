package com.example.appointment_booking_system.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class RescheduleRequestDTO {

    private LocalDate newDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public RescheduleRequestDTO() {
    }

    public RescheduleRequestDTO(LocalDate newDate, LocalTime startTime, LocalTime endTime) {
        this.newDate = newDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getNewDate() {
        return newDate;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
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
