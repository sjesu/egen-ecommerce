package com.egen.oms.orders.utils;

import com.egen.oms.orders.model.OrderDetails;
import com.egen.oms.orders.model.db.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OrderJsonConverter implements AttributeConverter<OrderDetails, String> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(OrderDetails order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (Exception ex){
            throw new RuntimeException("Unable to convert Order Obj to String");
        }
    }

    @Override
    public OrderDetails convertToEntityAttribute(String orderDetails) {
        try {
            objectMapper.readValue(orderDetails,Order.class);
        }catch (Exception ex){
            throw new RuntimeException("Unable to convert to Order Object");
        }
        return null;
    }
}
