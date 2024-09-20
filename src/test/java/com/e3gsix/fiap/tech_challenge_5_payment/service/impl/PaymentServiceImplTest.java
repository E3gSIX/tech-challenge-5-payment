package com.e3gsix.fiap.tech_challenge_5_payment.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.e3gsix.fiap.tech_challenge_5_payment.clients.AdyenGatewayClient;
import com.e3gsix.fiap.tech_challenge_5_payment.clients.CredentialsClient;
import com.e3gsix.fiap.tech_challenge_5_payment.clients.ShoppingCartClient;
import com.e3gsix.fiap.tech_challenge_5_payment.controller.exception.NotAuthorizedException;
import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.request.PaymentRequest;
import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response.UserResponse;
import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.PaymentType;
import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.UserRole;
import com.e3gsix.fiap.tech_challenge_5_payment.repository.PaymentRepository;
import com.e3gsix.fiap.tech_challenge_5_payment.service.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private TokenService tokenService;
    @Mock
    private CredentialsClient credentialsClient;
    @Mock
    private ShoppingCartClient shoppingCartClient;
    @Mock
    private AdyenGatewayClient adyenGatewayClient;
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentServiceImpl underTest;

    @Test
    void execute_ShouldThrowNotAuthorizedException_WhenUserNotAuthorized() {
        String authorization = "Bearer token";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        PaymentRequest request = new PaymentRequest(UUID.fromString("3303da2f-cc0a-4a24-948f-653498440fe2"),
                1L, PaymentType.CREDIT);
        UserResponse userResponse = new UserResponse(UUID.fromString("3303da2f-cc0a-4a24-948f-653498440fe2"),
                "wrongUsername", UserRole.USER);

        when(credentialsClient.findById(UUID.fromString("3303da2f-cc0a-4a24-948f-653498440fe2"))).thenReturn(userResponse);
        when(tokenService.recoverToken(authorization)).thenReturn("token");
        when(tokenService.validateToken("token")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("authorizedUsername");

        assertThrows(NotAuthorizedException.class, () -> underTest.execute(authorization, request));
    }
}