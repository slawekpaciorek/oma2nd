package com.oma.dao;

import com.oma.model.Product;

import java.util.List;

public interface ProductDAO {

    void save(Product product);

    List<Product> getAll();

    Product getByID(long id);

    void remove(long id);

    Product getByCatNumber(String catalogId);
}
