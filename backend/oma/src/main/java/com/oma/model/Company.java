package com.oma.model;

import lombok.Data;
import java.util.ArrayList;

@Data
public class Company {

    private String name;
    private Address address;
    private int taxNumberId;    //NIP
    private ArrayList<User> users;
    private ArrayList<ProductsOrder> orders;
    private ArrayList<DeliveryPoint> deliveryPoints;

}
