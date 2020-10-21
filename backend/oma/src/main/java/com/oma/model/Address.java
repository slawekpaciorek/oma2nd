package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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


    public long getIdAddress() {
        return id;
    }

    public void setIdAddress(long id) {
        this.id = id;
    }
}
