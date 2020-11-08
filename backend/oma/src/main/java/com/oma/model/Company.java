package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue()
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

//  Constructors

    public Company(String name, int taxNumber) {
        this.name = name;
        this.taxNumberId = taxNumber;
    }

    //Wygenerowany equals i hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                name.equals(company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
