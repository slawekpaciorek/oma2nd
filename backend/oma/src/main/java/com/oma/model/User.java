package com.oma.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private int mobilePhone;

    @Enumerated(EnumType.STRING)
    private UserPrivileges privileges;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "Company_id", referencedColumnName = "id")
//    @JsonManagedReference
    private Company company;

    @OneToMany(mappedBy = "createdBy")
    private List<DeliveryPoint> deliveryPoints;

    @OneToMany(mappedBy = "createdBy")
    private List<ProductsOrder> ordersCreated;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(String name, String username, String privileges, int mobilePhone) {
        this.name = name;
        this.username = username;
        this.privileges = UserPrivileges.valueOf(privileges);
        this.mobilePhone = mobilePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, mobilePhone);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobilePhone=" + mobilePhone +
                ", privileges=" + privileges +
                ", company name=" + company.getName() +
                '}';
    }

    public void addDeliveryPoint(DeliveryPoint deliveryPoint) {
        if(deliveryPoints==null) {
            deliveryPoints = new ArrayList<>();
        }
        deliveryPoint.setCompany(this.company);
        deliveryPoint.setCreatedBy(this);
        deliveryPoints.add(deliveryPoint);
    }
}
