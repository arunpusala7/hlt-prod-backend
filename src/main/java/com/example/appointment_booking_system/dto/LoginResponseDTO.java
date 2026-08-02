package com.example.appointment_booking_system.dto;

public class LoginResponseDTO {

    private final String token;
    private final String role;
    private final Long userId;
    private final String name;
    private final String email;

    public LoginResponseDTO(String token, String role, Long userId, String name, String email) {
        this.token = token;
        this.role = role;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}