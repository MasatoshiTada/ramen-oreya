package com.example.service.impl;

import com.example.service.GoodsService;
import com.example.service.OrderService;
import com.example.service.dto.Goods;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;
    private final GoodsService goodsService;
    private final String orderServiceUrl;

    public OrderServiceImpl(@LoadBalanced RestTemplate restTemplate,
                            GoodsService goodsService,
                            @Value("${orders.url}") String orderServiceUrl) {
        this.restTemplate = restTemplate;
        this.goodsService = goodsService;
        this.orderServiceUrl = orderServiceUrl;
    }

    /**
     * API Gateway経由でorder-serviceから未提供の注文一覧を取得する。
     */
    @HystrixCommand(fallbackMethod = "createDefaultOrders")
    @Override
    public List<OrderSummary> findAllNotProvided(String shopId) {
        OrderSummary[] orders = restTemplate.getForObject(orderServiceUrl + "/shop/{shopId}", OrderSummary[].class, shopId);
        List<OrderSummary> orderList = Arrays.asList(orders);
        logger.info("OrderSummaryのサイズ＝" + orderList.size());
        logger.info("0番目のOrderDetails = " + orderList.get(0).getOrderDetails());

        Integer[] goodsIds = orderList.stream()
                .flatMap(orderSummary -> orderSummary.getOrderDetails().stream())
                .map(orderDetail -> orderDetail.getGoodsId())
                .toArray(Integer[]::new);
        Map<Integer, Goods> goodsMap = goodsService.findByIds(goodsIds);

        for (OrderSummary orderSummary : orderList) {
            for (OrderDetail orderDetail : orderSummary.getOrderDetails()) {
                orderDetail.setGoods(goodsMap.get(orderDetail.getGoodsId()));
            }
        }
        return orderList;
    }

    /**
     * 注文一覧取得に対するフォールバックメソッド。
     * キャッシュしていた注文一覧を返す。
     */
    public List<OrderSummary> createDefaultOrders(String shopId, Throwable throwable) {
        logger.error("order-serviceへの接続に失敗しました。フォールバックします。", throwable);
        return Collections.emptyList();
    }

    /**
     * 指定した注文を「提供済み」に更新する。
     * @param summaryId
     */
    @Override
    public void updateProvided(Integer summaryId) {
        restTemplate.patchForObject(orderServiceUrl + "/{summaryId}", null, Void.class, summaryId);
    }
}
