package com.e3gsix.fiap.tech_challenge_5_payment.controller.impl;

import com.e3gsix.fiap.tech_challenge_5_payment.controller.PaymentController;
import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.request.PaymentRequest;
import com.e3gsix.fiap.tech_challenge_5_payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.e3gsix.fiap.tech_challenge_5_payment.controller.impl.PaymentControllerImpl.URL_PAYMENT;

@RestController
@RequestMapping(URL_PAYMENT)
public class PaymentControllerImpl implements PaymentController {

    public static final String URL_PAYMENT = "/payments";

    private final PaymentService paymentService;

    public PaymentControllerImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    @PostMapping
    public ResponseEntity execute(
            @RequestHeader("Authorization") String authorization,
            @RequestBody PaymentRequest request
    ) {
        this.paymentService.execute(authorization, request);

        return ResponseEntity.noContent().build();
    }
}
