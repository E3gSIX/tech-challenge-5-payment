package com.e3gsix.fiap.tech_challenge_5_payment.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    Long shoppingCartId;
    PaymentType type;
    PaymentStatus status;
    LocalDateTime lastUpdate;
}
