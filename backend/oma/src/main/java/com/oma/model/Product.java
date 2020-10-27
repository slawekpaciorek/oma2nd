package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String tradeId;

    @Column
    private String catalogId;

    public Product(String name, String tradeId, String catalogId) {
        this.name = name;
        this.tradeId = tradeId;
        this.catalogId = catalogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return  name.equals(product.name) &&
                tradeId.equals(product.tradeId) &&
                catalogId.equals(product.catalogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tradeId, catalogId);
    }
}
