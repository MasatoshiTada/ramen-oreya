package com.example.repository;

import com.example.entity.Goods;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GoodsRepository extends Repository<Goods, Integer> {
    Goods findOne(Integer id);

    List<Goods> findAll();
}
