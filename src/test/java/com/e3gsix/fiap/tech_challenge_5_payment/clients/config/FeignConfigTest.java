package com.e3gsix.fiap.tech_challenge_5_payment.clients.config;

import feign.codec.ErrorDecoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FeignConfigTest {

	@Test
	public void testErrorDecoderBean() {
		FeignConfig feignConfig = new FeignConfig();
		ErrorDecoder errorDecoder = feignConfig.errorDecoder();
		assertNotNull(errorDecoder);
	}

}
