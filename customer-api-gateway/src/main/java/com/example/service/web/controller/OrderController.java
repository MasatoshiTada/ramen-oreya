package com.example.service.web.controller;

import com.example.service.OrderService;
import com.example.service.dto.OrderSummary;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

@Component
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @StreamListener(Processor.INPUT)
    public void postOrder(OrderSummary orderSummary) {
        orderService.order(orderSummary.shopId, orderSummary);
    }
}
