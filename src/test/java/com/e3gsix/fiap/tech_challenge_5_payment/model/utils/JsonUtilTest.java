package com.e3gsix.fiap.tech_challenge_5_payment.model.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonUtilTest {

    @Test
    public void testCreateObjectMapper() {
        ObjectMapper objectMapper = JsonUtil.createObjectMapper();

        // Verifica se o módulo JavaTimeModule foi registrado
        assertTrue(objectMapper.getRegisteredModuleIds().contains(JavaTimeModule.class.getName()));

        // Verifica se a configuração WRITE_DATES_AS_TIMESTAMPS está desabilitada
        assertFalse(objectMapper.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
    }
}