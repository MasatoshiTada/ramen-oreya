package com.example.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "order_detail")
    @SequenceGenerator(name = "order_detail",
            sequenceName = "seq_order_detail_id",
            initialValue = 1,
            allocationSize = 1)
    private Integer detailId;

    private Integer goodsId;

    private Integer amount;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
