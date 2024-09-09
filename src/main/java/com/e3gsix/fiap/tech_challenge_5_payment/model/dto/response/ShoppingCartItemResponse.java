package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response;

import java.math.BigDecimal;

public record ShoppingCartItemResponse(
        String name,
        BigDecimal price,
        Integer quantity
) {
}
