package com.example.controller;

import com.example.entity.Goods;
import com.example.repository.GoodsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/goods")
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

    @GetMapping("/findByIds")
    public List<Goods> findByIds(@RequestParam("id") Integer[] ids) {
        Map<Integer, Goods> goodsMap = goodsRepository.findAll()
                .stream()
                .collect(Collectors.toMap(goods -> goods.id, goods -> goods));
        List<Goods> goodsList = new ArrayList<>();
        for (Integer id : ids) {
            goodsList.add(goodsMap.get(id));
        }
        return goodsList;
    }
}
