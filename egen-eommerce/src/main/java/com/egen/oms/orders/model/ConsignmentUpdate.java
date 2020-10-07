package com.egen.oms.orders.model;

import lombok.Data;

@Data
public class ConsignmentUpdate {
    Integer orderLineId;
    String orderLineStatus;
    OrderLine orderLine;
    Integer cancelledQty;
    Integer fulfillingQty;
}
