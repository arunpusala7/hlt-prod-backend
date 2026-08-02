package com.example.appointment_booking_system.service;

//package com.example.appointment.service;


import com.example.appointment_booking_system.entity.Doctor;
import com.example.appointment_booking_system.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
    @Override
    public Doctor getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @Override
    public Doctor updateDoctorProfile(Long id, Doctor profileData) {
        Doctor doctor = getDoctorById(id);
        if (profileData.getAboutBio() != null) doctor.setAboutBio(profileData.getAboutBio());
        if (profileData.getExperienceYears() != null) doctor.setExperienceYears(profileData.getExperienceYears());
        if (profileData.getQualifications() != null) doctor.setQualifications(profileData.getQualifications());
        if (profileData.getHospitalAffiliation() != null) doctor.setHospitalAffiliation(profileData.getHospitalAffiliation());
        if (profileData.getSuccessStories() != null) doctor.setSuccessStories(profileData.getSuccessStories());
        if (profileData.getTestimonials() != null) doctor.setTestimonials(profileData.getTestimonials());
        if (profileData.getConsultationFee() != null) doctor.setConsultationFee(profileData.getConsultationFee());
        return doctorRepository.save(doctor);
    }
}

