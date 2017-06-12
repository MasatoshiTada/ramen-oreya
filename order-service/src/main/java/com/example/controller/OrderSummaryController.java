package com.example.controller;

import com.example.entity.OrderSummary;
import com.example.repository.OrderSummaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping("/shop/{shopId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity order(@PathVariable("shopId") String shopId, @RequestBody OrderSummary orderSummary) {
        orderSummary.setProvided(false);
        orderSummary.setShopId(shopId);
        logger.info("注文された商品 = " + orderSummary.getOrderDetails().size());
        orderSummaryRepository.save(orderSummary);
        URI location = createLocation(orderSummary.getSummaryId());
        return ResponseEntity.created(location).build();
    }

    private URI createLocation(Integer id) {
        URI location = MvcUriComponentsBuilder.fromController(this.getClass())
                .pathSegment(id.toString())
                .build().encode().toUri();
        return location;
    }

    @PatchMapping("/{summaryId}")
    @ResponseStatus(HttpStatus.OK)
    public void patchProvided(@PathVariable Integer summaryId) {
        orderSummaryRepository.updateProvided(summaryId);
    }
}
