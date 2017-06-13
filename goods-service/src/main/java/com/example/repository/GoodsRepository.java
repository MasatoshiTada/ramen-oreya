package com.example.repository;

import com.example.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    @Query("SELECT g FROM Goods g JOIN FETCH g.category WHERE g.id = :id")
    Goods findOne(@Param("id") Integer id);

    @Query("SELECT DISTINCT g FROM Goods g JOIN FETCH g.category ORDER BY g.id")
    List<Goods> findAll();
}
