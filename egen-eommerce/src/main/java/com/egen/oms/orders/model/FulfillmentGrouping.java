package com.egen.oms.orders.model;

import lombok.Data;

import java.util.List;

@Data
public class FulfillmentGrouping {
    FulfillmentType fulfillmentType;
    List<OrderLine> orderLines;
}
