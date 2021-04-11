package com.oma.services;

import com.oma.dao.ProductListDAO;
import com.oma.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductListServiceImplementation implements ProductListService {

    @Autowired
    ProductListDAO productListDAO;

    @Override
    public OrderItem getProductListById(long id) {
        return productListDAO.getProductFromProductsById(id);
    }

    @Override
    public List<OrderItem> getProductsList() {
        return productListDAO.getListOfProducts();
    }

    @Override
    public void saveProductList(OrderItem orderItem) {
        productListDAO.saveProductList(orderItem);
    }

    @Override
    public void updateProductList(long id, OrderItem orderItem) {
        productListDAO.updateProducts(id, orderItem);
    }

    @Override
    public void removeProductList(long id) {
        productListDAO.removeProducts(id);
    }
}
