package com.egen.oms.orders.model;

import lombok.Data;

@Data
public class Address {
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String country;
    String zipCode;
}
