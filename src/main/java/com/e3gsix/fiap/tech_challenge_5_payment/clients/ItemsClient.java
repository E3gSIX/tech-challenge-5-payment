package com.e3gsix.fiap.tech_challenge_5_payment.clients;

import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.response.ItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "items-client", url = "${api.client.items.url}/items")
public interface ItemsClient {
    @GetMapping(value = "/{id}")
    ItemResponse findById(@PathVariable Long id);
}
