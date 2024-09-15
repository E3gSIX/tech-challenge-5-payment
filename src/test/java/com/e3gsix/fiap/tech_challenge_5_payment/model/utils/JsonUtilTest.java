package com.e3gsix.fiap.tech_challenge_5_payment.model.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class JsonUtilTest {

    @Test
    public void testCreateObjectMapper() {
        ObjectMapper objectMapper = JsonUtil.createObjectMapper();

        // Verifica se a configuração WRITE_DATES_AS_TIMESTAMPS está desabilitada
        assertFalse(objectMapper.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
    }
}