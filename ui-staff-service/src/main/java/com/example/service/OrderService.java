package com.example.service;

import com.example.service.dto.OrderSummary;

import java.util.List;

public interface OrderService {

    List<OrderSummary> findAllNotProvided();

    void updateProvided(Integer summaryId);
}
