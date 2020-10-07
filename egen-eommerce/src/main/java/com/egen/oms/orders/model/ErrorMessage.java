package com.egen.oms.orders.model;

import lombok.Data;

@Data
public class ErrorMessage {
    String errorCode;
    String errorMessage;

    public ErrorMessage(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
