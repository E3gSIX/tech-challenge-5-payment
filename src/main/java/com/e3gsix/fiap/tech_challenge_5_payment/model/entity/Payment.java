package com.e3gsix.fiap.tech_challenge_5_payment.model.entity;

import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.PaymentStatus;
import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.PaymentType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    Long shoppingCartId;

    @Enumerated(EnumType.STRING)
    PaymentType type;

    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    BigDecimal value;

    LocalDateTime lastUpdate;

    public Payment() {
    }

    public Payment(
            String username,
            Long shoppingCartId,
            PaymentType type,
            PaymentStatus status,
            BigDecimal value,
            LocalDateTime lastUpdate
    ) {
        this.username = username;
        this.shoppingCartId = shoppingCartId;
        this.type = type;
        this.status = status;
        this.value = value;
        this.lastUpdate = lastUpdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.lastUpdate = LocalDateTime.now();
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
        this.lastUpdate = LocalDateTime.now();
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
        this.lastUpdate = LocalDateTime.now();
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
        this.lastUpdate = LocalDateTime.now();
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
        this.lastUpdate = LocalDateTime.now();
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
