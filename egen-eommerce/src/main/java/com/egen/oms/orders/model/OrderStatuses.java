package com.egen.oms.orders.model;

public enum OrderStatuses {
    CREATED("CREATED"),
    FULFILLMENT_PROCESSING("FULFILLMENT_PROCESSING"),
    FULFILLMENT_COMPLETE("FULFILLMENT_COMPLETE"),
    CANCELLED("CANCELLED");

    String value;

    OrderStatuses(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrderStatuses fromValue(String orderStatus){
        OrderStatuses[] orderStatuses= values();

        int length = orderStatuses.length;
        for (int i = 0; i < length; i++) {
            OrderStatuses status = orderStatuses[i];
            if (String.valueOf(status.value).equals(orderStatus)){
                return  status;
            }
        }
        return  null;
    }
}
