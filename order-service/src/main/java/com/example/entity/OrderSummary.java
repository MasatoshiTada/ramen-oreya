package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderSummary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "order_summary")
    @SequenceGenerator(name = "order_summary",
            sequenceName = "seq_order_summary_id",
            initialValue = 1,
            allocationSize = 1)
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "summary_id")
    public List<OrderDetail> orderDetails;

    @PrePersist
    public void prePersist() {
        orderTimestamp = LocalDateTime.now();
    }
}
