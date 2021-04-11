package com.oma.services;

import com.oma.model.ProductsOrder;

import java.util.List;

public interface OrderService {

    ProductsOrder getOrderById(long id);
    List<ProductsOrder> getOrders();
    void saveOrder(ProductsOrder order);
    void updateOrder(long id, ProductsOrder order);
    void removeOrder(long id);

}
