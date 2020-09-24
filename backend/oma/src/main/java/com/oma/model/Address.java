package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

    private String streetNameAndNumber;
    private String zipCode;
    private String City;
    private int mobilePhoneNumber;

}
