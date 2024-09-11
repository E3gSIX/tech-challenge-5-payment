package com.e3gsix.fiap.tech_challenge_5_payment.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartStatusTest {

    @Test
    void getShoppingCartStatus_withValue_successful() {
        assertEquals(ShoppingCartStatus.ACTIVE, ShoppingCartStatus.valueOf("ACTIVE"));
        assertEquals(ShoppingCartStatus.CANCELED, ShoppingCartStatus.valueOf("CANCELED"));
        assertEquals(ShoppingCartStatus.CONCLUDED, ShoppingCartStatus.valueOf("CONCLUDED"));
    }
}