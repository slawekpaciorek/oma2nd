package com.oma.services;

import com.oma.dao.PriceDAO;
import com.oma.model.Price;
import com.oma.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImplementation implements PriceService {

    @Autowired
    PriceDAO priceDAO;

    @Autowired
    ProductService productService;

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
        Product product = price.getProduct();
        if(product!=null){
            List<Product> products = productService.getProducts();
            if(!products.contains(product))
                productService.saveProduct(product);
        }
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
