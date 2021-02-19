package com.oma.dao;

import com.oma.model.ProductsOrder;

import java.util.List;

public interface OrderDao {

    public ProductsOrder getOrderById(long id);
    public List<ProductsOrder> getOrderList();
    public void saveOrder(ProductsOrder order);
    public void updateOrder(long id, ProductsOrder order);
    public void removeOrder(long id);

}
