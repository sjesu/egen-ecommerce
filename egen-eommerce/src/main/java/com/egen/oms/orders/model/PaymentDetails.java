package com.egen.oms.orders.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDetails {
    BigDecimal authAmount;
    String avsCode;
    String cvvCode;
    String cardToken;
    PaymentTypes paymentTypes;
}
