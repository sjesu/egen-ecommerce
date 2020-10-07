package com.egen.oms.orders.controller;

import com.egen.oms.orders.model.OrderDetails;
import com.egen.oms.orders.model.PaymentDetails;
import com.egen.oms.orders.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Operation(summary = "Create Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order Created", content = {@Content(mediaType = "application/json",
                             schema = @Schema(implementation = OrderDetails.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Unable to process request")})
    @PostMapping(value = "/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody OrderDetails orderDetails) throws JsonProcessingException {
        orderService.creaetOrder(orderDetails);
        return new ResponseEntity<>("123", HttpStatus.CREATED);
    }

    @Operation(summary = "Get payment details by Order_Num")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get payment details by Order_Num", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderDetails.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Payment details not found"),
            @ApiResponse(responseCode = "500", description = "Unable to process request")})
    @GetMapping(value = "/getPaymentDetails/{orderNum}")
    public ResponseEntity<List<PaymentDetails>> getPaymentDetails(@PathVariable String orderNum) {
        List<PaymentDetails> paymentDetails = orderService.getPaymentDetails(orderNum);
        return new ResponseEntity<>(paymentDetails, HttpStatus.OK);
    }

    @Operation(summary = "Get Order details by Order_Num")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order details found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderDetails.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Order details not found"),
            @ApiResponse(responseCode = "500", description = "Unable to process request")})
    @GetMapping(value = "/getOrderDetails/{orderNum}")
    public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable String orderNum) {
        OrderDetails orderDetails = orderService.getOrderDetails(orderNum);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @Operation(summary = "Cancel Order details by Order_Num")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cancel order details", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderDetails.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Order details not found"),
            @ApiResponse(responseCode = "500", description = "Unable to process request")})
    @PutMapping(value = "/cancelOrder/{orderNum}")
    public ResponseEntity<Boolean> cancelOrder(@PathVariable String orderNum) {
        boolean orderDetails = orderService.cancelOrder(orderNum);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

}
