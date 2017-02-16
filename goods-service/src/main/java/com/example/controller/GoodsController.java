package com.example.controller;

import com.example.entity.Goods;
import com.example.repository.GoodsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/goods")
public class GoodsController {

    private final GoodsRepository goodsRepository;

    public GoodsController(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @GetMapping
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Goods findOne(@PathVariable Integer id) {
        return goodsRepository.findOne(id);
    }

}
