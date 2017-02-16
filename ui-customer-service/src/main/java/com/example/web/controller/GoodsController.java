package com.example.web.controller;

import com.example.service.GoodsService;
import com.example.service.OrderService;
import com.example.service.dto.Goods;
import com.example.service.dto.OrderDetail;
import com.example.service.dto.OrderSummary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class GoodsController {

    private final GoodsService goodsService;
    private final OrderService orderService;

    public GoodsController(GoodsService goodsService, OrderService orderService) {
        this.goodsService = goodsService;
        this.orderService = orderService;
    }

    @GetMapping
    public String index(Model model) {
        List<Goods> goodsList = goodsService.findAll();
        model.addAttribute("goodsList", goodsList);
        return "index";
    }

    @PostMapping
    public String order(@RequestParam Map<String, String> params) {
        OrderSummary orderSummary = convertToOrderSummary(params);
        orderService.order(orderSummary);
        return "redirect:orderComplete";
    }

    @GetMapping("/orderComplete")
    public String orderComplete() {
        return "orderComplete";
    }

    private OrderSummary convertToOrderSummary(Map<String, String> params) {
        List<OrderDetail> orderDetailList = params.entrySet()
                .stream()
                .map(entry -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.goodsId = Integer.valueOf(entry.getKey().split("_")[1]);
                    orderDetail.amount = Integer.valueOf(entry.getValue());
                    return orderDetail;
                }).filter(orderDetail -> !orderDetail.amount.equals(0))
                .collect(Collectors.toList());
        OrderSummary orderSummary = new OrderSummary();
        orderSummary.orderDetails = orderDetailList;
        return orderSummary;
    }

}
