package com.egen.oms.orders.utils;

import com.egen.oms.orders.model.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AddressConverter implements AttributeConverter<Address, String> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Address address) {
        try {
            return objectMapper.writeValueAsString(address);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert Address object to String");
        }
    }

    @Override
    public Address convertToEntityAttribute(String address) {
        try {
            return objectMapper.readValue(address,Address.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert String to Address object");
        }
    }
}
