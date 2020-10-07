package com.egen.oms.orders.exception;

import com.egen.oms.orders.model.ErrorMessage;

import java.util.List;

public class DataValidationException extends RuntimeException {
    List<ErrorMessage> errorMessageList;

    public DataValidationException(List<ErrorMessage> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

    public List<ErrorMessage> getErrorMessageList() {
        return errorMessageList;
    }

    public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }
}
