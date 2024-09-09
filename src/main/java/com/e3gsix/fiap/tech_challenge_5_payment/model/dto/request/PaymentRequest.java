package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.request;

import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.PaymentType;

import java.util.UUID;

public record PaymentRequest(
        UUID userId,
        Long shoppingCartId,
        PaymentType type
) {
}
