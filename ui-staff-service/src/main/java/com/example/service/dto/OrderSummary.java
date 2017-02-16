package com.example.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class OrderSummary implements Serializable {

    public Integer summaryId;

    /**
     * 注文日時
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Tokyo")
    public LocalDateTime orderTimestamp;

    /**
     * お客さんに提供済みならばtrue
     */
    public Boolean provided;

    public List<OrderDetail> orderDetails;
}
