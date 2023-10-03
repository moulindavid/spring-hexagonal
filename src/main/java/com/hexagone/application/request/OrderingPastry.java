package com.hexagone.application.request;


import com.hexagone.application.order.Order;
import com.hexagone.application.payment.CreditCard;
import com.hexagone.application.payment.Payment;
import com.hexagone.application.payment.Receipt;

import java.util.UUID;

public interface OrderingPastry {
    Order placeOrder(Order order);

    Order updateOrder(UUID orderId, Order order);

    void cancelOrder(UUID orderId);

    Payment payOrder(UUID orderId, CreditCard creditCard);

    Receipt readReceipt(UUID orderId);

    Order takeOrder(UUID orderId);
}