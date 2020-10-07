package com.egen.oms.orders.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderLine {
    String productCategory;
    Integer sku;
    BigDecimal size;
    BigDecimal itemPrice;
    Integer quantity;
    String brand;
    String color;
    String description;
}
