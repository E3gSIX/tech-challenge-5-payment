package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response;

import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.ShoppingCartStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ShoppingCartResponse(
        Long id,
        UUID userId,
        List<ShoppingCartItemResponse> items,
        BigDecimal total,
        ShoppingCartStatus status
) {
}
