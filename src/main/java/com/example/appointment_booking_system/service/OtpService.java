package com.example.appointment_booking_system.service;

import com.example.appointment_booking_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final SecureRandom random = new SecureRandom();

    // Map storing email -> OtpData (OTP code and expiration timestamp)
    private final Map<String, OtpData> otpStorage = new ConcurrentHashMap<>();

    public OtpService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public static class OtpData {
        private final String code;
        private final Instant expiryTime;

        public OtpData(String code, Instant expiryTime) {
            this.code = code;
            this.expiryTime = expiryTime;
        }

        public String getCode() { return code; }
        public Instant getExpiryTime() { return expiryTime; }
    }

    public String sendOtp(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        String normalizedEmail = email.trim().toLowerCase();

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Email is already registered. Please login.");
        }

        // Generate 6-digit OTP
        int number = random.nextInt(900000) + 100000;
        String otpCode = String.valueOf(number);

        // Store OTP with 5 minute expiration
        Instant expiry = Instant.now().plusSeconds(300);
        otpStorage.put(normalizedEmail, new OtpData(otpCode, expiry));

        // Send OTP via Email
        emailService.sendOtpEmail(normalizedEmail, otpCode);

        return "Verification OTP sent to " + normalizedEmail;
    }

    public boolean verifyOtp(String email, String inputOtp) {
        if (email == null || inputOtp == null) return false;

        String normalizedEmail = email.trim().toLowerCase();
        OtpData data = otpStorage.get(normalizedEmail);

        if (data == null) {
            return false;
        }

        if (Instant.now().isAfter(data.getExpiryTime())) {
            otpStorage.remove(normalizedEmail);
            return false;
        }

        return data.getCode().equals(inputOtp.trim());
    }

    public void clearOtp(String email) {
        if (email != null) {
            otpStorage.remove(email.trim().toLowerCase());
        }
    }
}
