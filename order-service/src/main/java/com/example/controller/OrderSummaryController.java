package com.example.controller;

import com.example.entity.OrderSummary;
import com.example.repository.OrderSummaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderSummaryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OrderSummaryRepository orderSummaryRepository;

    public OrderSummaryController(OrderSummaryRepository orderSummaryRepository) {
        this.orderSummaryRepository = orderSummaryRepository;
    }

    @GetMapping("/shop/{shopId}")
    public List<OrderSummary> findAll(@PathVariable("shopId") String shopId) {
        return orderSummaryRepository.findAllByShopIdNotProvided(shopId);
    }

    @StreamListener(Sink.INPUT)
    public void order(OrderSummary orderSummary) {
        orderSummary.setProvided(false);
        logger.info("キューから受け取った注文詳細の数 = " + orderSummary.getOrderDetails().size());
        orderSummaryRepository.save(orderSummary);
    }

    @PatchMapping("/{summaryId}")
    @ResponseStatus(HttpStatus.OK)
    public void patchProvided(@PathVariable Integer summaryId) {
        orderSummaryRepository.updateProvided(summaryId);
    }
}
