package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductListRow {

    private Product product;
    private int quantity;
    private double price;
    private double value;

}
