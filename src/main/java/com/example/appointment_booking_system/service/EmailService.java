package com.example.appointment_booking_system.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired; // Added
import org.springframework.beans.factory.annotation.Value; // Optional: read from properties
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async; // ✅ Import this
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // ✅ ADD @Async HERE
    // This allows the waitlist logic to finish instantly while email sends in background
    @Async
    public void sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom(fromEmail); // Ensure this matches application.properties

            javaMailSender.send(message);
            System.out.println("✅ (Async) Email Sent Successfully to: " + toEmail);
        } catch (Exception e) {
            System.err.println("❌ Error sending email: " + e.getMessage());
        }
    }

    // Your existing attachment method (can also be Async if you want)
    @Async
    public void sendEmailWithAttachment(String toEmail, String subject, String body, byte[] pdfBytes) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body);
            helper.addAttachment("Appointment_Ticket.pdf", new ByteArrayResource(pdfBytes));

            javaMailSender.send(message);
            System.out.println("📧 (Async) PDF Email sent to: " + toEmail);

        } catch (MessagingException e) {
            System.err.println("❌ Failed to send PDF email: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendCancellationEmail(String toEmail, String userName, String doctorName, String date, String time, String reason, String ticketId) {
        String subject = "❌ Appointment Cancelled - Ticket #" + (ticketId != null ? (ticketId.length() >= 8 ? ticketId.substring(0, 8) : ticketId) : "N/A");
        String body = String.format(
                "Hello %s,\n\n" +
                "Your appointment with Dr. %s scheduled for %s at %s has been cancelled.\n\n" +
                "Reason for Cancellation: %s\n" +
                "Ticket ID: %s\n\n" +
                "If you wish to book a new appointment or check availability, please visit the HealthConnect portal.\n\n" +
                "Best Regards,\n" +
                "HealthConnect Team",
                userName, doctorName, date, time, (reason != null && !reason.trim().isEmpty() ? reason : "Not specified"), ticketId
        );
        sendEmail(toEmail, subject, body);
    }

    @Async
    public void sendRescheduleEmail(String toEmail, String userName, String doctorName, String newDate, String newTime, String ticketId) {
        String subject = "📅 Appointment Rescheduled - Ticket #" + (ticketId != null ? (ticketId.length() >= 8 ? ticketId.substring(0, 8) : ticketId) : "N/A");
        String body = String.format(
                "Hello %s,\n\n" +
                "Your appointment with Dr. %s has been successfully rescheduled.\n\n" +
                "Updated Appointment Details:\n" +
                "Date: %s\n" +
                "Time: %s\n" +
                "Ticket ID: %s\n\n" +
                "Please present your ticket details when arriving for your consultation.\n\n" +
                "Best Regards,\n" +
                "HealthConnect Team",
                userName, doctorName, newDate, newTime, ticketId
        );
        sendEmail(toEmail, subject, body);
    }

    @Async
    public void sendOtpEmail(String toEmail, String otpCode) {
        String subject = "🔑 HealthConnect - Email Verification Code: " + otpCode;
        String body = String.format(
                "Hello,\n\n" +
                "Your One-Time Verification Code (OTP) for HealthConnect registration is:\n\n" +
                "   %s\n\n" +
                "This code is valid for 5 minutes. Please do not share this code with anyone.\n\n" +
                "Best Regards,\n" +
                "HealthConnect Team",
                otpCode
        );
        sendEmail(toEmail, subject, body);
    }
}