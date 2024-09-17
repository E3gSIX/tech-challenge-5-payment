package com.e3gsix.fiap.tech_challenge_5_payment.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.e3gsix.fiap.tech_challenge_5_payment.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.List;

import static com.e3gsix.fiap.tech_challenge_5_payment.controller.impl.PaymentControllerImpl.URL_PAYMENT;
import static com.e3gsix.fiap.tech_challenge_5_payment.swagger.SwaggerConfig.URL_SWAGGER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityFilterTest {
    @Mock
    private TokenService tokenService;

    @InjectMocks
    private SecurityFilter securityFilter;

    @Mock
    private FilterChain filterChain;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void doFilterInternal_permittedEndpoint_proceedWithNoAuthentication() throws ServletException, IOException {
        request.setRequestURI(URL_SWAGGER);
        request.setMethod(HttpMethod.GET.name());

        securityFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertFalse(response.getContentAsString().contains("Token não foi encontrado."));
        assertFalse(response.getContentAsString().contains("Token recebido não é válido."));
    }

    @Test
    public void doFilterInternal_noToken_shouldReturnUnauthorized() throws ServletException, IOException {
        request.setRequestURI(URL_PAYMENT);
        request.setMethod(HttpMethod.POST.name());

        securityFilter.doFilterInternal(request, response, filterChain);

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Token não foi encontrado."));
    }

    @Test
    public void doFilterInternal_invalidToken_shouldReturnUnauthorized() throws ServletException, IOException {
        request.setRequestURI(URL_PAYMENT);
        request.setMethod(HttpMethod.POST.name());
        String token = "valid.token";
        String authorization = "Bearer " + token;
        request.addHeader("Authorization", authorization);

        when(tokenService.recoverToken(anyString())).thenReturn(token);

        when(tokenService.validateToken(anyString())).thenReturn(null);

        securityFilter.doFilterInternal(request, response, filterChain);

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Token recebido não é válido."));
    }

    @Test
    public void doFilterInternal_validToken_shouldAuthenticateAndProceed() throws ServletException, IOException {
        request.setRequestURI(URL_PAYMENT);
        request.setMethod(HttpMethod.POST.name());
        String token = "valid.token";
        String authorization = "Bearer " + token;
        request.addHeader("Authorization", authorization);

        when(tokenService.recoverToken(anyString())).thenReturn(token);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(tokenService.validateToken(anyString())).thenReturn(decodedJWT);
        when(tokenService.getAuthoritiesFromToken(decodedJWT)).thenReturn(List.of());

        securityFilter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }
}