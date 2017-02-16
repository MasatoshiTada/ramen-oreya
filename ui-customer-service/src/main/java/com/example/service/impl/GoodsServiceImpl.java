package com.example.service.impl;

import com.example.service.GoodsService;
import com.example.service.dto.Goods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final RestTemplate restTemplate;
    private final String goodsServiceUrl;

    public GoodsServiceImpl(@LoadBalanced RestTemplate restTemplate, @Value("${goods-service.url}") String goodsServiceUrl) {
        this.restTemplate = restTemplate;
        this.goodsServiceUrl = goodsServiceUrl;
    }

    @Override
    public List<Goods> findAll() {
        // FIXME Eurekaで名前解決できてない
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(goodsServiceUrl + "v1/goods")).build();
        ResponseEntity<List<Goods>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Goods>>() {});
        List<Goods> goodsList = responseEntity.getBody();
        return goodsList;
    }
}
