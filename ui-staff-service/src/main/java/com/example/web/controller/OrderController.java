package com.example.web.controller;

import com.example.service.OrderService;
import com.example.service.dto.OrderSummary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String index(Model model) {
        List<OrderSummary> orderSummaryList = orderService.findAllNotProvided();
        model.addAttribute("orderSummaryList", orderSummaryList);
        return "index";
    }

    @PostMapping("/{summaryId}")
    public String updateProvided(@PathVariable Integer summaryId) {
        orderService.updateProvided(summaryId);
        return "redirect:/";
    }

}
