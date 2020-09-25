package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceListRow {

    private Product product;
    private Company company;
    private double price;

}
