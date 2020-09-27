package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
public class ProductListRow {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "Product_id", referencedColumnName = "id")
    private Product product;

    @Column
    private int quantity;

    private double price;

    private double value;


}
