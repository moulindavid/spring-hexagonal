package com.hexagone.infrastructure.adapter.rest;

import com.hexagone.application.order.Order;
import com.hexagone.application.payment.Receipt;
import com.hexagone.application.request.OrderingPastry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ReceiptController {
    private final OrderingPastry orderingPastry;

    @GetMapping("/receipt/{id}")
    ResponseEntity<Receipt> readReceipt(@PathVariable UUID id) {
        var receipt = orderingPastry.readReceipt(id);
        return ResponseEntity.ok(receipt);
    }

    @DeleteMapping("/receipt/{id}")
    ResponseEntity<Order> completeOrder(@PathVariable UUID id) {
        var order = orderingPastry.takeOrder(id);
        return ResponseEntity.ok(order);
    }
}