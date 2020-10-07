package com.egen.oms.orders.model;

public enum FulfillmentType {
    PICKUP("PICKUP"),
    SHIP("SHIP"),
    CURBSIDE_DELIVERY("CURBSIDE_DELIVERY");

    String value;

    FulfillmentType(String value) {
        this.value = value;
    }

    public static FulfillmentType fromValue(String fulfillmentType) {
        FulfillmentType[] fulfillmentTypes = values();

        int length = fulfillmentTypes.length;
        for (int i = 0; i < length; i++) {
            FulfillmentType ffmType = fulfillmentTypes[i];
            if (String.valueOf(ffmType.value).equals(fulfillmentType)) {
                return ffmType;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
