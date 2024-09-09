package com.e3gsix.fiap.tech_challenge_5_payment.repository;

import com.e3gsix.fiap.tech_challenge_5_payment.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
