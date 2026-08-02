package com.example.appointment_booking_system.controller;

import com.example.appointment_booking_system.dto.LoginRequestDTO;
import com.example.appointment_booking_system.dto.LoginResponseDTO;
import com.example.appointment_booking_system.dto.RegisterRequestDTO;
import com.example.appointment_booking_system.entity.Role;
import com.example.appointment_booking_system.entity.User;
import com.example.appointment_booking_system.security.CustomUserDetails;
import com.example.appointment_booking_system.security.JwtTokenProvider;
import com.example.appointment_booking_system.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final com.example.appointment_booking_system.service.EmailService emailService;
    private final com.example.appointment_booking_system.service.OtpService otpService;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          com.example.appointment_booking_system.service.EmailService emailService,
                          com.example.appointment_booking_system.service.OtpService otpService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.emailService = emailService;
        this.otpService = otpService;
    }

    @GetMapping("/test-email")
    public String sendTestEmail(@RequestParam(defaultValue = "pusalaarunkumar7@gmail.com") String to) {
        emailService.sendEmail(
                to,
                "🏥 Health Connect - Test Email Notification",
                "Hello,\n\nThis is a sample test email from Health Connect OS Backend!\nYour Gmail SMTP integration is working properly.\n\nBest Regards,\nHealth Connect Team"
        );
        return "Test email triggered successfully to: " + to;
    }

    // ===============================
    // SEND OTP FOR REGISTRATION
    // ===============================
    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody java.util.Map<String, String> payload) {
        String email = payload.get("email");
        return otpService.sendOtp(email);
    }

    // ===============================
    // VERIFY OTP
    // ===============================
    @PostMapping("/verify-otp")
    public java.util.Map<String, Object> verifyOtp(@RequestBody java.util.Map<String, String> payload) {
        String email = payload.get("email");
        String otp = payload.get("otp");
        boolean valid = otpService.verifyOtp(email, otp);

        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("valid", valid);
        response.put("message", valid ? "OTP verified successfully" : "Invalid or expired OTP");
        return response;
    }

    // ===============================
    // REGISTER USER / ADMIN WITH OTP
    // ===============================
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO requestDTO) {

        // Validate OTP if registering as USER
        if (requestDTO.getOtp() != null && !requestDTO.getOtp().trim().isEmpty()) {
            boolean validOtp = otpService.verifyOtp(requestDTO.getEmail(), requestDTO.getOtp());
            if (!validOtp) {
                throw new RuntimeException("Invalid or expired OTP verification code");
            }
        }

        Role role = Role.valueOf(requestDTO.getRole().toUpperCase());

        userService.registerUser(
                requestDTO.getName(),
                requestDTO.getEmail(),
                requestDTO.getPassword(),
                role
        );

        otpService.clearOtp(requestDTO.getEmail());

        return "User registered successfully";
    }


    // ===============================
    // LOGIN
    // ===============================
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO requestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getEmail(),
                        requestDTO.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtTokenProvider.generateToken(userDetails);

        // ← CHANGE ONLY THIS LINE
        return new LoginResponseDTO(
                token,
                userDetails.getRole().name(),
                userDetails.getUser().getId(),
                userDetails.getUser().getName(),
                userDetails.getUser().getEmail()
        );
    }
}
