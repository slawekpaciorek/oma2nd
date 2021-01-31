package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name = "User_id", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "Address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "Comapny_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "deliveryPoint")
    private List<ProductsOrder> orders;

    @Override
    public String toString() {
        return "DeliveryPoint{" +
                "name='" + name + '\'' +
                ", createdBy=" + createdBy +
                ", address=" + address +
                ", company=" + company +
                ", orders=" + orders +
                '}';
    }
}
