package com.oma.services;

import com.oma.dao.PriceDAO;
import com.oma.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImplementation implements PriceService {

    @Autowired
    PriceDAO priceDAO;

    @Override
    public Price getPriceById(long id) {
        return priceDAO.getPriceById(id);
    }

    @Override
    public List<Price> getPrices() {
        return priceDAO.getPrices();
    }

    @Override
    public void savePrice(Price price) {
        priceDAO.savePrice(price);
    }

    @Override
    public void updatePrice(long id, Price price) {
        priceDAO.updatePrice(id, price);
    }

    @Override
    public void removePrice(long id) {
        priceDAO.removePrice(id);
    }

}
