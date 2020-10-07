package com.egen.oms.orders.model;

public enum PaymentTypes {

    CREDITCARD("CREDITCARD"),
    PAYPAL("PAYPAL");

    String value;

    PaymentTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static PaymentTypes fromValue(String paymentType){
        PaymentTypes[] paymentTypes= values();

        int length = paymentTypes.length;
        for (int i = 0; i < length; i++) {
            PaymentTypes type = paymentTypes[i];
            if (String.valueOf(type.value).equals(paymentType)){
                return  type;
            }
        }
        return  null;
    }
}
