package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String streetNameAndNumber;

    @Column
    private String zipCode;

    @Column
    private String City;

    @Column
    private int mobilePhoneNumber;

    public Address(String streetNameAndNumber, String zipCode, String city, int mobilePhoneNumber) {
        this.streetNameAndNumber = streetNameAndNumber;
        this.zipCode = zipCode;
        City = city;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }
}
