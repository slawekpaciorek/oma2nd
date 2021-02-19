package com.oma.services;

import com.oma.model.ProductList;

import java.util.List;

public interface ProductListService {

    ProductList getProductListById(long id);
    List<ProductList> getProductsList();
    void saveProductList(ProductList productList);
    void updateProductList(long id, ProductList productList);
    void removeProductList(long id);

}
