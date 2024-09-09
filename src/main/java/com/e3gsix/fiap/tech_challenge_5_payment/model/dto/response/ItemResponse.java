package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response;

import java.math.BigDecimal;

public record ItemResponse(
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}

