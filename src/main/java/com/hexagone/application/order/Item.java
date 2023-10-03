package com.hexagone.application.order;

import com.hexagone.application.shared.Pastry;

import java.math.BigDecimal;

public record Item(Pastry pastry, BigDecimal cost, int quantity) {
}
