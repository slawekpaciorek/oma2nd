package com.oma.services;

import com.oma.model.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    List<Product> getProducts();

    Product getProductByID(long id);

    void updateProduct(Product product);

    void deleteProduct(long id);

    Product getProductByCatNumber(String catalogId);
}
