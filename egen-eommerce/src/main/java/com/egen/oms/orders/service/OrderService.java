package com.egen.oms.orders.service;

import com.egen.oms.orders.exception.DataValidationException;
import com.egen.oms.orders.exception.ObjectNotFoundException;
import com.egen.oms.orders.exception.OrderServiceException;
import com.egen.oms.orders.model.*;
import com.egen.oms.orders.model.db.Order;
import com.egen.oms.orders.repository.OrdeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
   private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    OrdeRepository ordeRepository;

    public OrderService(OrdeRepository ordeRepository) {
        this.ordeRepository = ordeRepository;
    }

    public boolean creaetOrder(OrderDetails orderDetails) {
        validateOrderDetails(orderDetails);
        try {
            Order order = new Order();
            order.setBillingAddress(orderDetails.getBillingAddress());
            order.setShippingAddress(orderDetails.getShippingAddress());
            order.setCreatedDate(orderDetails.orderCreationDate);
            order.setOrderJson(orderDetails);
            order.setOrderNum(orderDetails.getOrderNum());
            order.setOrderStatus(OrderStatuses.CREATED.getValue());
            order.setPaymentJson(orderDetails.getPaymentDetails());
            ordeRepository.save(order);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred while saving order details");
            throw new OrderServiceException("Exception occurred while saving order details");
        }

        sendMessageToFulfillmentService(orderDetails);

        return true;
    }

    private void sendMessageToFulfillmentService(OrderDetails orderDetails) {
        try {
            if (orderDetails.getFulfillmentGrouping().stream().anyMatch(fulfillmentGrouping -> fulfillmentGrouping.getFulfillmentType().equals(FulfillmentType.PICKUP)
                    || fulfillmentGrouping.getFulfillmentType().equals(FulfillmentType.CURBSIDE_DELIVERY))) {
                System.out.println("send message to PICKUP Fulfillment service");
                LOGGER.info("Message sent to PICKUP Fulfillment service");

            }

            if (orderDetails.getFulfillmentGrouping().stream().anyMatch(fulfillmentGrouping -> fulfillmentGrouping.getFulfillmentType().equals(FulfillmentType.SHIP))) {
                System.out.println("send message to SHIP Fulfillment service");
                LOGGER.info("Message sent to SHIP Fulfillment service");

            }
        } catch (Exception ex) {
            LOGGER.error("Error occurred while sending message to Fulfillment service");
            throw new OrderServiceException("Error occurred while sending message to Fulfillment service");
        }
    }

    public boolean cancelOrder(String orderNum) {

        System.out.println("We can call down stream system to cancel the order");

        if(!false){ // IF DOWN STREAM SYSTEM UNABLE TO CANCEL THE ORDER
            throw new OrderServiceException("Unable to cancel the order");
        }

        Order order = getDBOrder(orderNum);
        try {
            order.setOrderStatus(OrderStatuses.CANCELLED.getValue());
            ordeRepository.save(order);
        }catch (Exception exception){
            LOGGER.error("Error occurred while updating the order status");
            throw new OrderServiceException("Error occurred while updating the order status");
        }
        return true;
    }

    public List<PaymentDetails> getPaymentDetails(String orderNum) {
        return getDBOrder(orderNum).getPaymentJson();
    }

    public OrderDetails getOrderDetails(String orderNum) {
        return getDBOrder(orderNum).getOrderJson();
    }

    public Order getDBOrder(String orderNum) {
        try {
            Optional<Order> orderDetails = ordeRepository.findByOrderNum(orderNum);

            if (!orderDetails.isPresent()) {
                throw new ObjectNotFoundException("Order details not found");
            }
            return orderDetails.get();
        } catch (Exception ex) {
            LOGGER.error("Order details not found");
            throw ex;
        }
    }

    private void validateOrderDetails(OrderDetails orderDetails) {
        List<ErrorMessage> errorList = new ArrayList<>();

        if (orderDetails.getPaymentDetails() == null) {
            errorList.add(new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "PaymentDetails is required"));
        }
        if (orderDetails.getPaymentDetails().stream().anyMatch(paymentDetails -> paymentDetails.getAuthAmount() == null)) {
            errorList.add(new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Auth amount is required"));
        }

        if (orderDetails.getPaymentDetails().stream().anyMatch(paymentDetails -> paymentDetails.getCardToken() == null || paymentDetails.getCardToken().isEmpty())) {
            errorList.add(new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Cardtoken value is required"));
        }

        if (orderDetails.getTotalAmt().compareTo(BigDecimal.ZERO) < 0) {
            errorList.add(new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Order total cannot be less than 0"));
        }

        BigDecimal authAmt = orderDetails.getPaymentDetails().stream().map(PaymentDetails::getAuthAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (authAmt.compareTo(orderDetails.getTotalAmt()) < 0) {
            errorList.add(new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Auth amount is less than order total"));
        }

        if (orderDetails.getFulfillmentGrouping().size() <= 0) {
            errorList.add(new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Fulfillment details are required"));
        }

        orderDetails.getFulfillmentGrouping().forEach(fulfillmentGrouping -> {
            if (fulfillmentGrouping.getOrderLines().size() <= 0) {

                errorList.add(new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "OrderLine details are required"));
            }
        });

        if (errorList.size() > 0) {
            LOGGER.error("Order data validation failed");
            throw new DataValidationException(errorList);
        }

        LOGGER.info("Order data validation success");
    }
}
