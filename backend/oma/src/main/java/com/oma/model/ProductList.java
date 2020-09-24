package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ProductList {

    private ArrayList<ProductListRow> products;
    private double summary;

}
