package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response;

import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.ShoppingCartStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartResponseTest {

    @Test
    void testShoppgingCartResponse() {
        UUID uuid = UUID.randomUUID();
        List<ShoppingCartItemResponse> items = List.of(new ShoppingCartItemResponse("Test", BigDecimal.ONE, 4));
        ShoppingCartResponse response = new ShoppingCartResponse(1L, uuid, items, BigDecimal.TEN, ShoppingCartStatus.ACTIVE);

        assertEquals(1L, response.id());
        assertEquals(uuid, response.userId());
        assertEquals(items, response.items());
        assertEquals(BigDecimal.TEN, response.total());
        assertEquals(ShoppingCartStatus.ACTIVE, response.status());

    }

}
