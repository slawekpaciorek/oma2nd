package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

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
    private String city;

    @Column
    private int mobilePhoneNumber;

    public Address(String streetNameAndNumber, String zipCode, String city) {
        this.streetNameAndNumber = streetNameAndNumber;
        this.zipCode = zipCode;
        this.city = city;
    }


    public long getIdAddress() {
        return id;
    }

    public void setIdAddress(long id) {
        this.id = id;
    }

    public Address(String streetName){
        this.streetNameAndNumber = streetName;
    }
}
