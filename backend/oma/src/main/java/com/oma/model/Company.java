package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Company {

    private String name;
    private Address address;
    private int taxNumberId;    //NIP
    private ArrayList<User> users;
    private ArrayList<ProductsOrder> orders;
    private ArrayList<DeliveryPoint> deliveryPoints;

}
