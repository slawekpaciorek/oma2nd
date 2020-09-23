package com.oma.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProductList {

    private ArrayList<ProductListRow> products;
    private double summary;

}
