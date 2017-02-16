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
    public Integer detailId;

    public Integer goodsId;

    public Integer amount;
}
