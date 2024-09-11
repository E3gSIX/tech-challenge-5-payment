package com.e3gsix.fiap.tech_challenge_5_payment.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(tokenService, "secret", "test-secret");
    }

    @Test
    public void validateToken_ShouldReturnDecodedToken_WhenTokenIsValid() {
        // Given
        String token = JWT.create()
                .withIssuer("auth-api")
                .withSubject("testUser")
                .sign(Algorithm.HMAC256("test-secret"));

        // When
        DecodedJWT decodedJWT = tokenService.validateToken(token);

        // Then
        assertNotNull(decodedJWT);
        assertEquals("auth-api", decodedJWT.getIssuer());
        assertEquals("testUser", decodedJWT.getSubject());
    }

    @Test
    public void validateToken_ShouldReturnNull_WhenTokenIsInvalid() {
        // Given
        String token = "invalid-token";

        // When
        DecodedJWT decodedJWT = tokenService.validateToken(token);

        // Then
        assertNull(decodedJWT);
    }

    @Test
    public void recoverToken_ShouldReturnJustTokenValue_WhenTokenWithBearerIsSent() {
        String authorizationHeader = "Bearer abcdef12345";
        String expectedToken = "abcdef12345";

        String actualToken = tokenService.recoverToken(authorizationHeader);

        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void getAuthoritiesFromToken_shouldReturnExpectedAuthorities() {
        DecodedJWT jwt = mock(DecodedJWT.class);
        when(jwt.getClaim("authorities")).thenReturn(Mockito.mock(Claim.class));
        when(jwt.getClaim("authorities").toString()).thenReturn("[\"ROLE_USER\", \"ROLE_ADMIN\"]");

        List<GrantedAuthority> expectedAuthorities = List.of(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );

        Collection<? extends GrantedAuthority> actualAuthorities = tokenService.getAuthoritiesFromToken(jwt);

        assertEquals(expectedAuthorities, actualAuthorities);
    }
}