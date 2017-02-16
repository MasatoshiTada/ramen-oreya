package com.example.service.impl;

import com.example.common.exception.GoodsCacheContradictionException;
import com.example.service.GoodsService;
import com.example.service.dto.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;
    private final String goodsServiceUrl;
    private final DiscoveryClient discoveryClient;

    private Map<Integer, Goods> goodsCache;

    public GoodsServiceImpl(@LoadBalanced RestTemplate restTemplate,
                            @Value("${goods-service.url}") String goodsServiceUrl,
                            DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.goodsServiceUrl = goodsServiceUrl;
        this.discoveryClient = discoveryClient;
    }

//    @PostConstruct
    @Override
    public void loadAll() {
        // FIXME Eurekaで名前解決できてない(DiscoveryClientでIPアドレスやポート番号は取得できる)
        List<ServiceInstance> instances = discoveryClient.getInstances("goods-service");
        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String uri = serviceInstance.getUri().toString();
        logger.info(instances.toString());
        logger.info("host:port = " + host + ":" + port);
        logger.info("uri = " + uri);
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(goodsServiceUrl + "v1/goods")).build();
        ResponseEntity<List<Goods>> responseEntity = restTemplate.exchange(goodsServiceUrl + "v1/goods", HttpMethod.GET, null, new ParameterizedTypeReference<List<Goods>>() {});
        goodsCache = responseEntity.getBody()
                .stream()
                .collect(Collectors.toMap(goods -> goods.id, goods -> goods));
        String json = restTemplate.getForObject(goodsServiceUrl + "v1/goods", String.class);
        logger.info(json);
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
