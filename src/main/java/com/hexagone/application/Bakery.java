package com.hexagone.application;

import com.hexagone.application.order.Order;
import com.hexagone.application.payment.CreditCard;
import com.hexagone.application.payment.Payment;
import com.hexagone.application.payment.Receipt;
import com.hexagone.application.request.OrderingPastry;
import com.hexagone.application.response.Orders;
import com.hexagone.application.response.Payments;
import com.hexagone.architecture.UseCase;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
public class Bakery implements OrderingPastry {
    private final Orders orders;
    private final Payments payments;

    public Bakery(Orders orders, Payments payments) {
        this.orders = orders;
        this.payments = payments;
    }


    @Override
    public Order placeOrder(Order order) {
        return orders.save(order);
    }

    @Override
    public Order updateOrder(UUID orderId, Order order) {
        var existingOrder = orders.findOrderById(orderId);

        return orders.save(existingOrder.update(order));
    }

    @Override
    public void cancelOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        if (!order.canBeCancelled()) {
            throw new IllegalStateException("Order is already paid");
        }

        orders.deleteById(orderId);
    }

    @Override
    public Payment payOrder(UUID orderId, CreditCard creditCard) {
        var order = orders.findOrderById(orderId);

        orders.save(order.markPaid());

        return payments.save(new Payment(orderId, creditCard, LocalDate.now()));
    }

    @Override
    public Receipt readReceipt(UUID orderId) {
        var order = orders.findOrderById(orderId);
        var payment = payments.findPaymentByOrderId(orderId);

        return new Receipt(order.getCost(), payment.paid());
    }

    @Override
    public Order takeOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        return orders.save(order.markTaken());
    }
}
