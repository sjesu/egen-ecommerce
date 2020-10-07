package com.egen.oms.orders.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateHistory {
    String orderNum;
    List<ConsignmentUpdate> consignmentUpdateList;
}
