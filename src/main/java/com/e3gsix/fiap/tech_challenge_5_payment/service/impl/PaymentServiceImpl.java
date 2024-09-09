package com.e3gsix.fiap.tech_challenge_5_payment.service.impl;

import com.e3gsix.fiap.tech_challenge_5_payment.clients.AdyenGatewayClient;
import com.e3gsix.fiap.tech_challenge_5_payment.clients.CredentialsClient;
import com.e3gsix.fiap.tech_challenge_5_payment.clients.ShoppingCartClient;
import com.e3gsix.fiap.tech_challenge_5_payment.controller.exception.NotAuthorizedException;
import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.request.PaymentRequest;
import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response.PaymentIntegrityResponse;
import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response.ShoppingCartResponse;
import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response.UserResponse;
import com.e3gsix.fiap.tech_challenge_5_payment.model.entity.Payment;
import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.PaymentStatus;
import com.e3gsix.fiap.tech_challenge_5_payment.repository.PaymentRepository;
import com.e3gsix.fiap.tech_challenge_5_payment.service.PaymentService;
import com.e3gsix.fiap.tech_challenge_5_payment.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final TokenService tokenService;
    private final CredentialsClient credentialsClient;
    private final ShoppingCartClient shoppingCartClient;
    private final AdyenGatewayClient adyenGatewayClient;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(
            TokenService tokenService,
            CredentialsClient credentialsClient,
            ShoppingCartClient shoppingCartClient,
            AdyenGatewayClient adyenGatewayClient,
            PaymentRepository paymentRepository
    ) {
        this.tokenService = tokenService;
        this.credentialsClient = credentialsClient;
        this.shoppingCartClient = shoppingCartClient;
        this.adyenGatewayClient = adyenGatewayClient;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void execute(String authorization, PaymentRequest request) {
        UserResponse userFound = this.credentialsClient.findById(request.userId());

        validatePermission(authorization, userFound);

        PaymentIntegrityResponse integrityResponse = this.shoppingCartClient.checkIntegrityForPayment(
                authorization, request.shoppingCartId(), request.userId()
        );

        if (!integrityResponse.integrity()) {
            throw new UnsupportedOperationException(integrityResponse.reason());
        }

        ShoppingCartResponse shoppingCartResponse = shoppingCartClient.findById(
                authorization, request.shoppingCartId(), request.userId()
        );

        Payment payment = new Payment(
                getUsernameFromAuthorization(authorization),
                request.shoppingCartId(),
                request.type(),
                PaymentStatus.CREATED,
                shoppingCartResponse.total(),
                LocalDateTime.now()
        );

        this.paymentRepository.save(payment);

        adyenGatewayClient.pay(payment);

        this.paymentRepository.save(payment);

        this.shoppingCartClient.conclude(authorization, request.shoppingCartId(), request.userId());

        payment.setStatus(PaymentStatus.EXECUTED);

        this.paymentRepository.save(payment);
    }

    private void validatePermission(String authorization, UserResponse userResponse) {
        String usernameToken = getUsernameFromAuthorization(authorization);

        boolean isInsecureDirectObjectReferenceVulnerability = !usernameToken.equals(userResponse.username());
        if (isInsecureDirectObjectReferenceVulnerability) {
            throw new NotAuthorizedException("Este usuário não têm permissão para realizar essa ação.");
        }
    }

    private String getUsernameFromAuthorization(String authorization) {
        String token = this.tokenService.recoverToken(authorization);
        return this.tokenService.validateToken(token).getSubject();
    }
}
