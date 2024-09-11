package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentIntegrityResponseTest {

    @Test
    void testPaymentIntegrityResponse(){
        PaymentIntegrityResponse response = new PaymentIntegrityResponse(Boolean.TRUE,"Response test");

        assertEquals(Boolean.TRUE,response.integrity());
        assertEquals("Response test",response.reason());
    }
}
