package com.example.appointment_booking_system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "slot_duration_minutes", nullable = false)
    private Integer slotDurationMinutes;

    public Availability() {
    }

    public Availability(Long id, Doctor doctor, LocalDate date, LocalTime startTime, LocalTime endTime, Integer slotDurationMinutes) {
        this.id = id;
        this.doctor = doctor;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotDurationMinutes = slotDurationMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

    public Integer getSlotDurationMinutes() {
        return slotDurationMinutes;
    }

    public void setSlotDurationMinutes(Integer slotDurationMinutes) {
        this.slotDurationMinutes = slotDurationMinutes;
    }

    public static AvailabilityBuilder builder() {
        return new AvailabilityBuilder();
    }

    public static class AvailabilityBuilder {
        private Long id;
        private Doctor doctor;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;
        private Integer slotDurationMinutes;

        public AvailabilityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AvailabilityBuilder doctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public AvailabilityBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public AvailabilityBuilder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public AvailabilityBuilder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public AvailabilityBuilder slotDurationMinutes(Integer slotDurationMinutes) {
            this.slotDurationMinutes = slotDurationMinutes;
            return this;
        }

        public Availability build() {
            return new Availability(id, doctor, date, startTime, endTime, slotDurationMinutes);
        }
    }
}
