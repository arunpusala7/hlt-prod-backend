package com.example.appointment_booking_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
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

    public Doctor() {
    }

    public Doctor(Long id, String name, String specialization, String email, Double consultationFee, String aboutBio, String experienceYears, String qualifications, String hospitalAffiliation, String successStories, String testimonials) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.consultationFee = consultationFee != null ? consultationFee : 500.0;
        this.aboutBio = aboutBio;
        this.experienceYears = experienceYears;
        this.qualifications = qualifications;
        this.hospitalAffiliation = hospitalAffiliation;
        this.successStories = successStories;
        this.testimonials = testimonials;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getAboutBio() {
        return aboutBio;
    }

    public void setAboutBio(String aboutBio) {
        this.aboutBio = aboutBio;
    }

    public String getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(String experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getHospitalAffiliation() {
        return hospitalAffiliation;
    }

    public void setHospitalAffiliation(String hospitalAffiliation) {
        this.hospitalAffiliation = hospitalAffiliation;
    }

    public String getSuccessStories() {
        return successStories;
    }

    public void setSuccessStories(String successStories) {
        this.successStories = successStories;
    }

    public String getTestimonials() {
        return testimonials;
    }

    public void setTestimonials(String testimonials) {
        this.testimonials = testimonials;
    }

    public static DoctorBuilder builder() {
        return new DoctorBuilder();
    }

    public static class DoctorBuilder {
        private Long id;
        private String name;
        private String specialization;
        private String email;
        private Double consultationFee = 500.0;
        private String aboutBio;
        private String experienceYears;
        private String qualifications;
        private String hospitalAffiliation;
        private String successStories;
        private String testimonials;

        public DoctorBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DoctorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DoctorBuilder specialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public DoctorBuilder email(String email) {
            this.email = email;
            return this;
        }

        public DoctorBuilder consultationFee(Double consultationFee) {
            this.consultationFee = consultationFee;
            return this;
        }

        public DoctorBuilder aboutBio(String aboutBio) {
            this.aboutBio = aboutBio;
            return this;
        }

        public DoctorBuilder experienceYears(String experienceYears) {
            this.experienceYears = experienceYears;
            return this;
        }

        public DoctorBuilder qualifications(String qualifications) {
            this.qualifications = qualifications;
            return this;
        }

        public DoctorBuilder hospitalAffiliation(String hospitalAffiliation) {
            this.hospitalAffiliation = hospitalAffiliation;
            return this;
        }

        public DoctorBuilder successStories(String successStories) {
            this.successStories = successStories;
            return this;
        }

        public DoctorBuilder testimonials(String testimonials) {
            this.testimonials = testimonials;
            return this;
        }

        public Doctor build() {
            return new Doctor(id, name, specialization, email, consultationFee, aboutBio, experienceYears, qualifications, hospitalAffiliation, successStories, testimonials);
        }
    }
}
