package com.e3gsix.fiap.tech_challenge_5_payment.clients;

import com.e3gsix.fiap.tech_challenge_5_payment.model.entity.Payment;
import com.e3gsix.fiap.tech_challenge_5_payment.model.enums.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class AdyenGatewayClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean pay(Payment payment) {
        payment.setStatus(PaymentStatus.PROCESSING);
        try {
            Thread.sleep(randomNum(1, 3) * 1000);
            payment.setStatus(PaymentStatus.PAID);
            logger.info("Ok!");
        } catch (InterruptedException e) {
            logger.error("Fail!");
            throw new RuntimeException(e);
        }

        return true;
    }

    private int randomNum(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
