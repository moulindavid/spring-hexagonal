package com.hexagone.application.response;

import com.hexagone.application.order.Order;

import java.util.UUID;

public interface Orders {
    Order findOrderById(UUID orderId) throws OrderNotFound;

    Order save(Order order);

    void deleteById(UUID orderId);
}