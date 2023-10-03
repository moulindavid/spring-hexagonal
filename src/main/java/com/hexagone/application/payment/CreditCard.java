package com.hexagone.application.payment;

import java.time.Month;
import java.time.Year;

public record CreditCard(String cardHolderName, String cardNumber, Month expirationMonth, Year expirationYear) {
}
