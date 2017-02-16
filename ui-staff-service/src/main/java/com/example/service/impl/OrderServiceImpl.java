package com.example.service.impl;

import com.example.service.GoodsService;
import com.example.service.OrderService;
import com.example.service.dto.OrderDetail;
import com.example.service.dto.OrderSummary;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;
    private final GoodsService goodsService;
    private final String orderServiceUrl;

    public OrderServiceImpl(@LoadBalanced RestTemplate restTemplate,
                            GoodsService goodsService,
                            @Value("${order-service.url}") String orderServiceUrl) {
        this.restTemplate = restTemplate;
        this.goodsService = goodsService;
        this.orderServiceUrl = orderServiceUrl;
    }

    @HystrixCommand(fallbackMethod = "createDefaultOrders")
    @Override
    public List<OrderSummary> findAllNotProvided() {
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(orderServiceUrl + "orders")).build();
        ResponseEntity<List<OrderSummary>> responseEntity =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<OrderSummary>>() {
                });
        List<OrderSummary> orderList = responseEntity.getBody();
        logger.info("OrderSummaryのサイズ＝" + orderList.size());
        logger.info("0番目のOrderDetails = " + orderList.get(0).orderDetails);
        for (OrderSummary orderSummary : orderList) {
            for (OrderDetail orderDetail : orderSummary.orderDetails) {
                orderDetail.goods = goodsService.findById(orderDetail.goodsId);
            }
        }
        return orderList;
    }

    public List<OrderSummary> createDefaultOrders() {
        logger.error("order-serviceへの接続に失敗しました。フォールバックします。");
        return Collections.emptyList();
    }

    @Override
    public void updateProvided(Integer summaryId) {
        restTemplate.patchForObject(orderServiceUrl + "orders/{summaryId}", null, Void.class, summaryId);
    }
}
