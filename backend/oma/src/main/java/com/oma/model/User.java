package com.oma.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

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

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
    })
    @JoinColumn(name = "Company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "createdBy")
    private List<DeliveryPoint> deliveryPoints;

    @OneToMany(mappedBy = "createdBy")
    private List<ProductsOrder> ordersCreated;


    public User(String name, String username, String privileges, int mobilePhone) {
        this.name = name;
        this.username = username;
        this.privileges = UserPrivileges.valueOf(privileges);
        this.mobilePhone = mobilePhone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(int mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public UserPrivileges getPrivileges() {
        return privileges;
    }

    public void setPrivileges(UserPrivileges privileges) {
        this.privileges = privileges;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<DeliveryPoint> getDeliveryPoints() {
        return deliveryPoints;
    }

    public void setDeliveryPoints(List<DeliveryPoint> deliveryPoints) {
        this.deliveryPoints = deliveryPoints;
    }

    public List<ProductsOrder> getOrdersCreated() {
        return ordersCreated;
    }

    public void setOrdersCreated(List<ProductsOrder> ordersCreated) {
        this.ordersCreated = ordersCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                mobilePhone == user.mobilePhone &&
                name.equals(user.name) &&
                username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, mobilePhone);
    }
}
