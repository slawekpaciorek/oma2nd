package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class DeliveryPoint {

    private String name;
    private User createdBy;
    private Address address;
    private Company company;
    private ArrayList<ProductsOrder> orders;

}
