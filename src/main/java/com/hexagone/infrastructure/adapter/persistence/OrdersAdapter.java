package com.hexagone.infrastructure.adapter.persistence;

import com.hexagone.application.shared.OrderNotFound;
import com.hexagone.infrastructure.adapter.persistence.entity.OrderEntity;
import com.hexagone.application.order.Order;
import com.hexagone.application.response.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrdersAdapter implements Orders {
    private final OrderRepository orderRepository;

    @Override
    public Order findOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(OrderEntity::toDomain)
                .orElseThrow(OrderNotFound::new);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(OrderEntity.fromDomain(order)).toDomain();
    }

    @Override
    public void deleteById(UUID orderId) {
        orderRepository.deleteById(orderId);
    }
}