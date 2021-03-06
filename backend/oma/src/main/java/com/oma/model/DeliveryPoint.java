package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
public class DeliveryPoint {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "comapny_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "deliveryPoint")
    private List<ProductsOrder> orders;

    public DeliveryPoint(String name, Address address) {
        this.setName(name);
        this.setAddress(address);
    }

    public DeliveryPoint(String name) {
        setName(name);
    }

    @Override
    public String toString() {
        return "DeliveryPoint{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", company=" + Optional.ofNullable(company) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryPoint that = (DeliveryPoint) o;
        return Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, company);
    }
}
