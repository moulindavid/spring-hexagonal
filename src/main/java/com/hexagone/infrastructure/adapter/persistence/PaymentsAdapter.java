package com.hexagone.infrastructure.adapter.persistence;

import com.hexagone.application.payment.Payment;
import com.hexagone.application.response.Payments;
import com.hexagone.infrastructure.adapter.persistence.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor

public class PaymentsAdapter implements Payments {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment findPaymentByOrderId(UUID orderId) {
        return paymentRepository.findByOrderId(orderId)
                .map(PaymentEntity::toDomain)
                .orElseThrow();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(PaymentEntity.fromDomain(payment)).toDomain();
    }
}