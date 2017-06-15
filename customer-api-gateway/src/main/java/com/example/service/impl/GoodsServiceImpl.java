package com.example.service.impl;

import com.example.service.GoodsService;
import com.example.service.dto.Goods;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @HystrixCommand(fallbackMethod = "getDefaultGoods")
    @Override
    public List<Goods> findAll() {
        return null;
    }

    /**
     * findAll()のフォールバックメソッド。
     * goods-serviceから前回取得した値を、Redisキャッシュから取得する。
     */
    public List<Goods> getDefaultGoods(Throwable throwable) {
        return null;
    }
}
