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
//@RequiredArgsConstructor
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

}
