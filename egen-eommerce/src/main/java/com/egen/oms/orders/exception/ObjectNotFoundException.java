package com.egen.oms.orders.exception;

import com.egen.oms.orders.model.ErrorMessage;
import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends RuntimeException{
    ErrorMessage errorMessage;

    public ObjectNotFoundException(String message) {
        super(message);
        errorMessage = new ErrorMessage(String.valueOf(HttpStatus.NOT_FOUND),message);
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
