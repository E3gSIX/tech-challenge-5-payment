package com.e3gsix.fiap.tech_challenge_5_payment.clients;

import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response.PaymentIntegrityResponse;
import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response.ShoppingCartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "shopping-carts-client", url = "${api.client.shopping-cart.url}/shopping-carts")
public interface ShoppingCartClient {
    @GetMapping(value = "/{id}")
    ShoppingCartResponse findById(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id,
            @RequestParam UUID userId
    );

    @GetMapping(value = "/{id}/payment-integrity")
    PaymentIntegrityResponse checkIntegrityForPayment(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id,
            @RequestParam UUID userId
    );

    @PostMapping(value = "/{id}")
    ResponseEntity conclude(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id,
            @RequestParam UUID userId
    );
}
