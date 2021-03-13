package com.oma.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table (name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column
    private String username;

    @NotBlank
    @Size(max = 100)
    @Column
    private String password;

    @Column
    private int mobilePhone;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private UserPrivileges privileges;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn (name = "role_id"))
    private Set<Role> roles = new HashSet<>();

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


    public User(String name, String username, String privileges, int mobilePhone,
                String email, String password) {
        this.name = name;
        this.username = username;
        this.privileges = UserPrivileges.valueOf(privileges);
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.password = password;
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
}
