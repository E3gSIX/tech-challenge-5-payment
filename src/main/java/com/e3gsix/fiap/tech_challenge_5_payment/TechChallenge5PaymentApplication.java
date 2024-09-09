package com.e3gsix.fiap.tech_challenge_5_payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TechChallenge5PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechChallenge5PaymentApplication.class, args);
	}

}
