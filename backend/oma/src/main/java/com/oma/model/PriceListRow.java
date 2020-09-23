package com.oma.model;

import lombok.Data;

@Data
public class PriceListRow {

    private Product product;
    private Company company;
    private double price;

}
