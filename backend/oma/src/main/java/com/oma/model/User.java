package com.oma.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "username"
    }),
    @UniqueConstraint(columnNames = {
        "email"
    })
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank
    @Size(min=3, max = 50)
    private String name;

    @Column
    @NotBlank
    @Size(min=3, max = 50)
    private String username;

    @Column
    @NotBlank
    @Size(min=6, max = 100)
    private String password;

    @Column
    @NotBlank
    @Size(min=9, max = 15)
    private int mobilePhone;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String username, String privileges, int mobilePhone) {
        this.name = name;
        this.username = username;
        this.privileges = UserPrivileges.valueOf(privileges);
        this.mobilePhone = mobilePhone;
        this.email = email;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
