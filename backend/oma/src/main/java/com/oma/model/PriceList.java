package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class PriceList {

    private ArrayList<PriceListRow> products;

}
