package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Goods implements Serializable {

    @Id
    public Integer id;

    public String name;

    public Integer price;

    @ManyToOne
    public Category category;
}
