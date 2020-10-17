package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
public class PriceList {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "priceList")
    private List<PriceListRow> products;

}
