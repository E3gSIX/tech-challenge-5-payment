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
}
