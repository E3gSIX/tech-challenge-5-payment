package com.e3gsix.fiap.tech_challenge_5_payment.controller;

import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.request.PaymentRequest;
import org.springframework.http.ResponseEntity;

public interface PaymentController {
    ResponseEntity execute(String authorization, PaymentRequest request);
}
