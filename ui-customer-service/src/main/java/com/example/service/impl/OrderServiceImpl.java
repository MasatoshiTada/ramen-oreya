package com.example.service.impl;

import com.example.service.OrderService;
import com.example.service.dto.OrderSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;
    private final String orderServiceUrl;

    public OrderServiceImpl(@LoadBalanced RestTemplate restTemplate,
                            @Value("${order-service.url}") String orderServiceUrl) {
        this.restTemplate = restTemplate;
        this.orderServiceUrl = orderServiceUrl;
    }

    // TODO POST操作に@HystrixCommandは付けるのか？
    @Override
    public void order(OrderSummary orderSummary) {
        restTemplate.postForLocation(orderServiceUrl + "orders", orderSummary);
    }
}
