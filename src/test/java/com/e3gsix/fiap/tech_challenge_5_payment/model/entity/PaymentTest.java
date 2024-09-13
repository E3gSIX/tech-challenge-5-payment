package com.e3gsix.fiap.tech_challenge_5_payment.model.entity;

import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.PaymentStatus;
import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.PaymentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaymentTest {

    @Test
    void testItemConstructor_ValidItem_ShouldSetCorrectly() {
        Payment payment = new Payment(
                "user123",
                1L,
                PaymentType.CREDIT,
                PaymentStatus.PAID,
                new BigDecimal("100.00"),
                LocalDateTime.now()
        );

        assertEquals("user123", payment.getUsername());
        assertEquals(1L, payment.getShoppingCartId());
        assertEquals(PaymentType.CREDIT, payment.getType());
        assertEquals(PaymentStatus.PAID, payment.getStatus());
        assertEquals(new BigDecimal("100.00"), payment.getValue());
        assertNotNull(payment.getLastUpdate());
    }

    @Test
    void testSetName_ValidName_ShouldSetCorrectly() {
        Payment payment = new Payment();
        payment.setUsername("newUser");
        assertEquals("newUser", payment.getUsername());
        assertNotNull(payment.getLastUpdate());
    }

    @Test
    void testSetShoppingCartId_ValidValue_ShouldSetCorrectly() {
        Payment payment = new Payment();
        payment.setShoppingCartId(2L);
        assertEquals(2L, payment.getShoppingCartId());
        assertNotNull(payment.getLastUpdate());
    }

    @Test
    void testSetType_ValidValue_ShouldSetCorrectly() {
        Payment payment = new Payment();
        payment.setType(PaymentType.CREDIT);
        assertEquals(PaymentType.CREDIT, payment.getType());
        assertNotNull(payment.getLastUpdate());
    }

    @Test
    void testSetStatus_ValidValue_ShouldSetCorrectly() {
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.PAID);
        assertEquals(PaymentStatus.PAID, payment.getStatus());
        assertNotNull(payment.getLastUpdate());
    }

    @Test
    void testSetValue_ValidValue_ShouldSetCorrectly() {
        Payment payment = new Payment();
        payment.setValue(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, payment.value);
        assertNotNull(payment.getLastUpdate());
    }

    @Test
    void testSetLastDate_ValidDate_ShouldSetCorrectly(){
        Payment payment = new Payment();
        LocalDateTime testDate = LocalDateTime.now();
        payment.setLastUpdate(testDate);
        assertEquals(testDate,payment.getLastUpdate());
    }
}
