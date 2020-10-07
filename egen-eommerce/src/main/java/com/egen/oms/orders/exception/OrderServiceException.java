package com.egen.oms.orders.exception;

import com.egen.oms.orders.model.ErrorMessage;
import org.springframework.http.HttpStatus;

public class OrderServiceException extends RuntimeException{

    ErrorMessage errorMessage;

    public OrderServiceException(String message) {
        super(message);
        errorMessage = new ErrorMessage(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR),message);
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
