package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response;

public record PaymentIntegrityResponse(
        Boolean integrity,
        String reason
) {
}
