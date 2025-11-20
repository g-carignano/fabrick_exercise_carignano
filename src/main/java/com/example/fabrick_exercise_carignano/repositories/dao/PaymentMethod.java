package com.example.fabrick_exercise_carignano.repositories.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table( name = "D_PAYMENT_METHOD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentMethod {

    private BigDecimal paymentMethodId;
    private String circuitType;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pay_seq")
    @SequenceGenerator(name = "pay_seq", sequenceName = "ID_SEQ", allocationSize = 1)
    @Column(name = "PAYMENTMETHODID", unique = true, nullable = false)
    public BigDecimal getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(BigDecimal paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Column(name = "CIRCUITTYPE")
    public String getCircuitType() {
        return circuitType;
    }

    public void setCircuitType(String circuitType) {
        this.circuitType = circuitType;
    }

    public PaymentMethod(BigDecimal paymentMethodId, String circuitType) {
        this.paymentMethodId = paymentMethodId;
        this.circuitType = circuitType;
    }

    public PaymentMethod() {
    }
}
