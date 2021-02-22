package com.oma.services;

import com.oma.dao.OrderDao;
import com.oma.model.ProductsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    OrderDao orderDao;

    @Override
    public ProductsOrder getOrderById(long id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<ProductsOrder> getOrders() {
        return orderDao.getOrderList();
    }

    @Override
    public void saveOrder(ProductsOrder order) {
        orderDao.saveOrder(order);
    }

    @Override
    public void updateOrder(long id, ProductsOrder order) {
        orderDao.updateOrder(id, order);
    }

    @Override
    public void removeOrder(long id) {
        orderDao.removeOrder(id);
    }
}
