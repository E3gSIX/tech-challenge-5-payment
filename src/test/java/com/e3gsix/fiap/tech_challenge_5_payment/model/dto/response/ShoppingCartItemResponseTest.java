package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartItemResponseTest {

    @Test
    void testShoppingCartItemResponse(){
        ShoppingCartItemResponse response = new ShoppingCartItemResponse("Test", BigDecimal.ONE,4);

        assertEquals("Test",response.name());
        assertEquals(BigDecimal.ONE,response.price());
    }
}
