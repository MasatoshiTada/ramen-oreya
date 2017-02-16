package com.example.service.impl;

import com.example.common.exception.GoodsCacheContradictionException;
import com.example.service.GoodsService;
import com.example.service.dto.Goods;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;
    private final String goodsServiceUrl;

    private Map<Integer, Goods> goodsCache;

    public GoodsServiceImpl(@LoadBalanced RestTemplate restTemplate,
                            @Value("${goods-service.url}") String goodsServiceUrl) {
        this.restTemplate = restTemplate;
        this.goodsServiceUrl = goodsServiceUrl;
    }

    @HystrixCommand(fallbackMethod = "createDefaultGoods")
    @Override
    public void loadAll() {
        ResponseEntity<List<Goods>> responseEntity =
                restTemplate.exchange(goodsServiceUrl + "api/goods", HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Goods>>() {});
        goodsCache = responseEntity.getBody()
                .stream()
                .collect(Collectors.toMap(goods -> goods.id, goods -> goods));
        if (goodsCache.isEmpty()) {
            logger.error("商品が読み込めませんでした");
        } else {
            logger.info(goodsCache.size() + "件の商品を読み込みました");
        }
    }

    public void createDefaultGoods() {
        goodsCache = Collections.EMPTY_MAP;
    }

    @Override
    public Goods findById(Integer id) {
        if (goodsCache.containsKey(id)) {
            return goodsCache.get(id);
        } else {
            throw new GoodsCacheContradictionException("該当する商品が存在しません：id = " + id);
            // TODO この例外が発生した際の処理（@RestControllerAdvice?）
        }
    }
}
