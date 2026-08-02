package com.example.appointment_booking_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "consultation_fee")
    @Builder.Default
    private Double consultationFee = 500.0;

    @Column(name = "about_bio", length = 2000)
    private String aboutBio;

    @Column(name = "experience_years")
    private String experienceYears;

    @Column(name = "qualifications")
    private String qualifications;

    @Column(name = "hospital_affiliation")
    private String hospitalAffiliation;

    @Column(name = "success_stories", length = 2000)
    private String successStories;

    @Column(name = "testimonials", length = 2000)
    private String testimonials;
}
