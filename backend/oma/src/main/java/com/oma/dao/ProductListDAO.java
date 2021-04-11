package com.oma.dao;

import com.oma.model.OrderItem;

import java.util.List;

public interface ProductListDAO {

    OrderItem getProductFromProductsById(long id);
    List<OrderItem> getListOfProducts();
    void saveProductList(OrderItem products);
    void updateProducts(long id, OrderItem products);
    void removeProducts(long id);

}
