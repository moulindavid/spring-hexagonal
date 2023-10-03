package com.hexagone.infrastructure.adapter.rest;


import com.hexagone.application.payment.CreditCard;
import com.hexagone.application.payment.Payment;
import com.hexagone.application.request.OrderingPastry;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final OrderingPastry orderingPastry;

    @PutMapping("/payment/{id}")
    ResponseEntity<Payment> payOrder(@PathVariable UUID id, @Valid @RequestBody CreditCard creditCardRequest) {
        var payment = orderingPastry.payOrder(id,
                new CreditCard(
                        creditCardRequest.cardHolderName(),
                        creditCardRequest.cardNumber(),
                        creditCardRequest.expirationMonth(),
                        creditCardRequest.expirationYear()));
        return ResponseEntity.ok(payment);
    }
}