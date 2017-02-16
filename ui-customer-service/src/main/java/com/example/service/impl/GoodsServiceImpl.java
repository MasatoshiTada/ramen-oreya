package com.example.service.impl;

import com.example.service.GoodsService;
import com.example.service.dto.Goods;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
public class GoodsServiceImpl implements GoodsService {

    private final RestTemplate restTemplate;
    private final String goodsServiceUrl;

    public GoodsServiceImpl(@LoadBalanced RestTemplate restTemplate,
                            @Value("${goods-service.url}") String goodsServiceUrl) {
        this.restTemplate = restTemplate;
        this.goodsServiceUrl = goodsServiceUrl;
    }

    @HystrixCommand(fallbackMethod = "getDefaultGoods")
    @Override
    public List<Goods> findAll() {
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(goodsServiceUrl + "api/goods")).build();
        ResponseEntity<List<Goods>> responseEntity =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Goods>>() {});
        List<Goods> goodsList = responseEntity.getBody();
        return goodsList;
    }

    public List<Goods> getDefaultGoods() {
        return Collections.emptyList();
    }
}
