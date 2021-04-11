package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(mappedBy = "basket")
    private List<ProductsOrder> order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column
    private int quantity;

    @Column
    private double summaryValue;

    public OrderItem(Price price, int quantity) {
        this.product = price.getProduct();
        this.quantity = quantity;
        this.summaryValue = quantity * price.getValue();
    }
}
