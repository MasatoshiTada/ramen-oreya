package com.example.repository;

import com.example.entity.Goods;
import org.springframework.data.repository.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "goods", path = "goods")
public interface GoodsRepository extends Repository<Goods, Integer> {
    Goods findOne(Integer id);

    List<Goods> findAll();
}
