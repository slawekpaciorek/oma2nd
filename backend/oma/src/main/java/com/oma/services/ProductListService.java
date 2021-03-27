package com.oma.services;

import com.oma.model.OrderItem;

import java.util.List;

public interface ProductListService {

    OrderItem getProductListById(long id);
    List<OrderItem> getProductsList();
    void saveProductList(OrderItem orderItem);
    void updateProductList(long id, OrderItem orderItem);
    void removeProductList(long id);

}
