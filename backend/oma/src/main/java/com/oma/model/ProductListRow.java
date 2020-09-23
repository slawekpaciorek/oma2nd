package com.oma.model;

import lombok.Data;

@Data
public class ProductListRow {

    private Product product;
    private int quantity;
    private double price;
    private double value;

}
