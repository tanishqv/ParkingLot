package com.scaler.models;

public class Payment extends BaseModel {
    private Double amount;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private String referenceNumber;
}
