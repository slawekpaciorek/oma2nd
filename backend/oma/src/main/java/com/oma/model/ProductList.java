package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ProductList {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(mappedBy = "products")
    private List<ProductsOrder> order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column
    private int quantity;

    @Column
    private int summaryValue;

}
