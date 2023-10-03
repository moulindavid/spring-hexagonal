package com.hexagone.infrastructure.adapter.rest;

import com.hexagone.application.order.Order;
import com.hexagone.application.request.OrderingPastry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderingPastry orderingPastry;

    @PostMapping("/order")
    ResponseEntity<Order> createOrder(@RequestBody Order orderRequest, UriComponentsBuilder uriComponentsBuilder) {
        var order = orderingPastry.placeOrder(orderRequest);
        var location = uriComponentsBuilder.path("/order/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        return ResponseEntity.created(location).body(order);
    }

    @PostMapping("/order/{id}")
    ResponseEntity<Order> updateOrder(@PathVariable UUID id, @RequestBody Order orderRequest) {
        var order = orderingPastry.updateOrder(id, orderRequest);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/order/{id}")
    ResponseEntity<Void> cancelOrder(@PathVariable UUID id) {
        orderingPastry.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }
}
