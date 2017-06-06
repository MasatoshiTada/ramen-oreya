package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class OrderSummary implements Serializable {

    /**
     * 注文ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "order_summary")
    @SequenceGenerator(name = "order_summary",
            sequenceName = "seq_order_summary_id",
            initialValue = 1,
            allocationSize = 1)
    private Integer summaryId;

    /**
     * 注文日時
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Tokyo")
    private LocalDateTime orderTimestamp;

    /**
     * 提供済みならばtrue
     */
    private Boolean provided;

    /**
     * 店舗ID
     */
    private String shopId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "summary_id")
    private List<OrderDetail> orderDetails;

    @PrePersist
    public void prePersist() {
        setOrderTimestamp(LocalDateTime.now());
    }

    public Integer getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Integer summaryId) {
        this.summaryId = summaryId;
    }

    public LocalDateTime getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(LocalDateTime orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public Boolean getProvided() {
        return provided;
    }

    public void setProvided(Boolean provided) {
        this.provided = provided;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
