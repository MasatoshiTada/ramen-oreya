package com.example.service;

import com.example.service.dto.Goods;

public interface GoodsService {

    void loadAll();

    Goods findById(Integer id);
}
