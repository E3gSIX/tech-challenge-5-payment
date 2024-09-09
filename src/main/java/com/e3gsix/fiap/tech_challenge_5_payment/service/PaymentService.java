package com.e3gsix.fiap.tech_challenge_5_payment.service;

import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.request.PaymentRequest;

public interface PaymentService {
    void execute(String authorization, PaymentRequest request);
}
