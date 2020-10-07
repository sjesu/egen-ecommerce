package com.egen.oms.orders.advice;

import com.egen.oms.orders.exception.DataValidationException;
import com.egen.oms.orders.exception.ObjectNotFoundException;
import com.egen.oms.orders.exception.OrderServiceException;
import com.egen.oms.orders.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = OrderServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage orderServiceException(OrderServiceException exception){
        return exception.getErrorMessage();
    }

    @ExceptionHandler(value = ObjectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundException(ObjectNotFoundException exception){
        return exception.getErrorMessage();
    }

    @ExceptionHandler(value = DataValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public List<ErrorMessage> notFoundException(DataValidationException exception){
        return exception.getErrorMessageList();
    }


}

