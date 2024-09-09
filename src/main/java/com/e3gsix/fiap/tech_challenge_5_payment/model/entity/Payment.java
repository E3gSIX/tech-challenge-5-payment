package com.e3gsix.fiap.tech_challenge_5_payment.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    Long shoppingCartId;
    PaymentType type;

    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    @Enumerated(EnumType.STRING)
    LocalDateTime lastUpdate;
}
