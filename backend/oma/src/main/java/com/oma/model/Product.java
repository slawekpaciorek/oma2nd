package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
