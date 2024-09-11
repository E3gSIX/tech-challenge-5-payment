package com.e3gsix.fiap.tech_challenge_5_payment.clients;

import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CredentialsClientTest {

    @Mock
    private CredentialsClient credentialsClient;

    @InjectMocks
    private CredentialsClientTest credentialsClientTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        UUID userId = UUID.randomUUID();
        UserResponse userResponse = new UserResponse(userId, null, null);

        when(credentialsClient.findById(userId)).thenReturn(userResponse);

        UserResponse response = credentialsClient.findById(userId);

        assertNotNull(response);
        assertEquals(userId, response.id());
        assertEquals(response, userResponse);
    }
}