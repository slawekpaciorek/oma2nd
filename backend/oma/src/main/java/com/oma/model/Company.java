package com.oma.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

    @Id
    @GeneratedValue()
    private long id;

    @Column
    private String name;

    @Column(unique = true)
    private int taxNumberId;    //NIP

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "company")
    private List<User> users;

    @OneToMany(mappedBy = "company")
    private List<ProductsOrder> orders;

    @OneToMany(mappedBy = "company")
    private List<DeliveryPoint> deliveryPoints;

//  Constructors

    public Company(String name, int taxNumber, Address address) {
        this.name = name;
        this.taxNumberId = taxNumber;
        this.address = address;
    }

    public Company(String name, int taxNumberId, Address address, List<User> users) {
        this.name = name;
        this.taxNumberId = taxNumberId;
        this.address = address;
        this.users = users;
    }

    //Wygenerowany equals i hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return taxNumberId == company.taxNumberId &&
                name.equals(company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
