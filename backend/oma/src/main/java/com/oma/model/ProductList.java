package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
public class ProductList {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(targetEntity = ProductListRow.class)
    @JoinColumn(name = "ProductListRow_id", referencedColumnName = "id")
    private List<ProductListRow> products;

    @Column
    private double summary;

}
