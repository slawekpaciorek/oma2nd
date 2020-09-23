package com.oma.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class User {

    private String name;
    private String username;
    private String password;
    private int mobilePhone;
    private UserPrivileges privileges;
    private Company company;
    private ArrayList<DeliveryPoint> deliveryPoints;
    private ArrayList<ProductsOrder> orders;

}
