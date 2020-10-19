package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column(unique = true)
    private int taxNumberId;    //NIP

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "Address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "company")
    private List<User> users;

    @OneToMany(mappedBy = "company")
    private List<ProductsOrder> orders;

    @OneToMany(mappedBy = "company")
    private List<DeliveryPoint> deliveryPoints;

    public Company(String name) {
        this.name = name;
    }

}
