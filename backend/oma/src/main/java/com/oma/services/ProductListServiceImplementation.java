package com.oma.services;

import com.oma.dao.ProductListDAO;
import com.oma.model.ProductList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductListServiceImplementation implements ProductListService {

    @Autowired
    ProductListDAO productListDAO;

    @Override
    public ProductList getProductListById(long id) {
        return productListDAO.getProductFromProductsById(id);
    }

    @Override
    public List<ProductList> getProductsList() {
        return productListDAO.getListOfProducts();
    }

    @Override
    public void saveProductList(ProductList productList) {
        productListDAO.saveProductList(productList);
    }

    @Override
    public void updateProductList(long id, ProductList productList) {
        productListDAO.updateProducts(id, productList);
    }

    @Override
    public void removeProductList(long id) {
        productListDAO.removeProducts(id);
    }
}
