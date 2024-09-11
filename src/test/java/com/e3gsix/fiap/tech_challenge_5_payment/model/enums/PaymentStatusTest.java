package com.e3gsix.fiap.tech_challenge_5_payment.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentStatusTest {

    @Test
    void getPaymentStatus_withValue_successful() {
        assertEquals(PaymentStatus.CREATED, PaymentStatus.valueOf("CREATED"));
        assertEquals(PaymentStatus.PROCESSING, PaymentStatus.valueOf("PROCESSING"));
        assertEquals(PaymentStatus.PAID, PaymentStatus.valueOf("PAID"));
        assertEquals(PaymentStatus.EXECUTED, PaymentStatus.valueOf("EXECUTED"));
    }
}