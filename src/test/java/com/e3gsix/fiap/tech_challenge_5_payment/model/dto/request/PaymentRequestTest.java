package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.request;

import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.PaymentType;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentRequestTest {

    @Test
    void testPaymentRequest() {
        UUID userId = UUID.randomUUID();
        Long shoppingCartId = 12345L;
        PaymentType type = PaymentType.CREDIT;

        PaymentRequest paymentRequest = new PaymentRequest(userId, shoppingCartId, type);

        assertEquals(userId, paymentRequest.userId());
        assertEquals(shoppingCartId, paymentRequest.shoppingCartId());
        assertEquals(type, paymentRequest.type());
    }
}