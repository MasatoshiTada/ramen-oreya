package com.example.controller;

import com.example.Sink2;
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

    @StreamListener(Sink2.INPUT)
    public void patchProvided(SummaryIdHolder summaryIdHolder) {
        orderSummaryRepository.updateProvided(summaryIdHolder.getSummaryId());
        logger.info("{}の注文を提供済みにしました", summaryIdHolder);
    }

    private static class SummaryIdHolder {
        private Integer summaryId;

        public SummaryIdHolder() {
        }

        public Integer getSummaryId() {
            return summaryId;
        }

        public void setSummaryId(Integer summaryId) {
            this.summaryId = summaryId;
        }

        @Override
        public String toString() {
            return "SummaryIdHolder{" +
                    "summaryId=" + summaryId +
                    '}';
        }
    }

}
