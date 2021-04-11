package com.oma.services;

import com.oma.dao.OrderDao;
import com.oma.dao.ProductListDAO;
import com.oma.model.ProductsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductListDAO productListDAO;

    @Override
    public ProductsOrder getOrderById(long id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<ProductsOrder> getOrders() {
        return orderDao.getOrderList();
    }

    @Override
    @Transactional
    public void saveOrder(ProductsOrder order) {
        if(order.getBasket()!=null){
            order.getBasket().forEach(x -> productListDAO.saveProductList(x));
        }
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
