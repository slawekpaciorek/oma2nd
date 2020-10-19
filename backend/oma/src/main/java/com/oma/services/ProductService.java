package com.oma.services;

import com.oma.model.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    List<Product> getProducts();
}
