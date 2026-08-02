package com.example.appointment_booking_system.dto;

public class CreateOrderResponse {
    private String orderId;
    private Double amount;
    private String currency;
    private String keyId;
    private String doctorName;
    private String specialization;
    private Double consultationFee;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(String orderId, Double amount, String currency, String keyId, String doctorName, String specialization, Double consultationFee) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.keyId = keyId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public static CreateOrderResponseBuilder builder() {
        return new CreateOrderResponseBuilder();
    }

    public static class CreateOrderResponseBuilder {
        private String orderId;
        private Double amount;
        private String currency;
        private String keyId;
        private String doctorName;
        private String specialization;
        private Double consultationFee;

        public CreateOrderResponseBuilder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public CreateOrderResponseBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public CreateOrderResponseBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public CreateOrderResponseBuilder keyId(String keyId) {
            this.keyId = keyId;
            return this;
        }

        public CreateOrderResponseBuilder doctorName(String doctorName) {
            this.doctorName = doctorName;
            return this;
        }

        public CreateOrderResponseBuilder specialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public CreateOrderResponseBuilder consultationFee(Double consultationFee) {
            this.consultationFee = consultationFee;
            return this;
        }

        public CreateOrderResponse build() {
            return new CreateOrderResponse(orderId, amount, currency, keyId, doctorName, specialization, consultationFee);
        }
    }
}
