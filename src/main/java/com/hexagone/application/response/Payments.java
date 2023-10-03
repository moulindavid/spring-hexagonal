package com.hexagone.application.response;

import com.hexagone.application.payment.Payment;

import java.util.UUID;

public interface Payments {
    Payment findPaymentByOrderId(UUID orderId);

    Payment save(Payment payment);
}