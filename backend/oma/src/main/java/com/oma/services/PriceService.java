package com.oma.services;

import com.oma.model.Price;
import java.util.List;

public interface PriceService {

    Price getPriceById(long id);
    List<Price> getPrices();
    void savePrice(Price price);
    void updatePrice(long id, Price price);
    void removePrice(long id);

}
