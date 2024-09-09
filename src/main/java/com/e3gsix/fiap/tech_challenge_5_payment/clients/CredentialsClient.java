package com.e3gsix.fiap.tech_challenge_5_payment.clients;

import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "credentials-client", url = "${api.client.credentials.url}/users")
public interface CredentialsClient {
    @GetMapping(value = "/{userId}")
    UserResponse findById(@PathVariable UUID userId);
}
