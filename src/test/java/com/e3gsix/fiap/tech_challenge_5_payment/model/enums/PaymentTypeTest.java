package com.e3gsix.fiap.tech_challenge_5_payment.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTypeTest {

    @Test
    void getPaymentType_withValue_successful() {
        assertEquals(PaymentType.BILL, PaymentType.valueOf("BILL"));
        assertEquals(PaymentType.CREDIT, PaymentType.valueOf("CREDIT"));
        assertEquals(PaymentType.DEBIT, PaymentType.valueOf("DEBIT"));
        assertEquals(PaymentType.PIX, PaymentType.valueOf("PIX"));
        assertEquals(PaymentType.CRYPTO, PaymentType.valueOf("CRYPTO"));

    }
}