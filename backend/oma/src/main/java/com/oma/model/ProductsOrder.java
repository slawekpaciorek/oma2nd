package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductsOrder {

    private Company company;
    private DeliveryPoint deliveryPoint;
    private ProductList products;
    private User createdBy;
    private User approvedBy;
    private OrderStatus status;
    private LocalDate createdAt;
    private double summaryValue;
    private String info;
}
