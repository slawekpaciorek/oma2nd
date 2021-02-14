package com.oma.dao;

import com.oma.model.ProductList;

import java.util.List;

public interface ProductListDAO {

    ProductList getProductFromProductsById(long id);
    List<ProductList> getListOfProducts();
    void saveProductList(ProductList products);
    void updateProducts(long id, ProductList products);
    void removeProducts(long id, ProductList products);

}
