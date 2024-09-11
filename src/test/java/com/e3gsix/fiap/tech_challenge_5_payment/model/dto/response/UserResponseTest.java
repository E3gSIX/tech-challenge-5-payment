package com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response;


import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.UserRole;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseTest {

    @Test
    void testUserResponse() {
        UUID uuid = UUID.randomUUID();
        UserResponse userResponse = new UserResponse(uuid, "Test", UserRole.USER);

        assertEquals(uuid, userResponse.id());
        assertEquals("Test", userResponse.username());
        assertEquals(UserRole.USER, userResponse.role());
    }
}
