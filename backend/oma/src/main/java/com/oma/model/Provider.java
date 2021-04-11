package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.oma.model.User;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Provider implements Serializable{

    @Id
    private int id;

    @Column
    private String name;

    @Column
    private String taxIdNumber;

    @OneToOne
    private Address address;

    @OneToMany
    private List<User> admins;

    public Provider(String name, String taxIdNumber, Address address) {
        this.name=name;
        this.taxIdNumber=taxIdNumber;
        this.address=address;
    }

    public void addAdmin(User user){
        if(admins==null){
            admins = new ArrayList<>();
        }
        user.setPrivileges(UserPrivileges.administrator);
        admins.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider)) return false;
        Provider provider = (Provider) o;
        return Objects.equals(name, provider.name) && Objects.equals(taxIdNumber, provider.taxIdNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, taxIdNumber);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taxIdNumber='" + taxIdNumber + '\'' +
                ", address=" + address +
                '}';
    }
}
