package com.egen.oms.orders.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDetails {

    public Date orderCreationDate;
    BigDecimal shippingAmt;
    BigDecimal shippingTaxAmt;
    BigDecimal totalAmt;
    BigDecimal totalTaxAmt;
    String orderNum;
    User user;
    Address shippingAddress;
    Address billingAddress;
    List<FulfillmentGrouping> fulfillmentGrouping;
    List<OrderUpdateHistory> orderUpdateHistories;
    List<PaymentDetails> paymentDetails;
}

