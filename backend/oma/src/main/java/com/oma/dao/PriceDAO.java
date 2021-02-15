package com.oma.dao;

import com.oma.model.Price;

import java.util.List;

public interface PriceDAO {

    public Price getPriceById(long id);
    public List<Price> getPrices();
    public void savePrice(Price price);
    public void updatePrice(long id, Price price);
    public void removePrice(long id);

}
