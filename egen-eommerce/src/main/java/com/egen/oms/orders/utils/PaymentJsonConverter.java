package com.egen.oms.orders.utils;

import com.egen.oms.orders.model.PaymentDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class PaymentJsonConverter implements AttributeConverter<List<PaymentDetails>,String> {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<PaymentDetails> paymentDetails) {
        try{
            return objectMapper.writeValueAsString(paymentDetails);
        } catch (JsonProcessingException e) {
            throw  new RuntimeException("Excpeption occurred while converting Payment details");
        }
    }

    @Override
    public List<PaymentDetails> convertToEntityAttribute(String paymentDetails) {
        try {
            return objectMapper.readValue(paymentDetails,List.class);
        } catch (JsonProcessingException e) {
            throw  new RuntimeException("Excpeption occurred while converting Payment details");
        }

    }
}
