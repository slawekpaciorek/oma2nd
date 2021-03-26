package com.oma.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = "idAddress")
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

    public Address(String streetName){
        this.streetNameAndNumber = streetName;
    }

    public Address(String street, String zipCode, String city, int mobilePhoneNumber) {
        this.streetNameAndNumber = street;
        this.zipCode = zipCode;
        this.city = city;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(streetNameAndNumber, address.streetNameAndNumber) && Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetNameAndNumber, zipCode, city);
    }
}
