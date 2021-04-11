package com.oma.dao;

import com.oma.model.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductListDAOImplementation implements ProductListDAO {

    @Autowired
    SessionFactory sessionFactory;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Override
    public OrderItem getProductFromProductsById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductList p where p.id = :id", OrderItem.class).getSingleResult();
    }

    @Transactional
    @Override
    public List<OrderItem> getListOfProducts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderItem ").getResultList();
    }

    @Transactional
    @Override
    public void saveProductList(OrderItem products) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(products);
    }

    @Transactional
    @Override
    public void updateProducts(long id, OrderItem products) {
        OrderItem orderItem = getProductFromProductsById(id);
        if(!orderItem.equals(products)){
            Session session = sessionFactory.getCurrentSession();
            if(orderItem.getQuantity()!=products.getQuantity())
                orderItem.setQuantity(products.getQuantity());
            session.update(orderItem);
        }
        log.info("Objects are the same");
    }

    @Transactional
    @Override
    public void removeProducts(long id) {
        OrderItem orderItem = getProductFromProductsById(id);
        Session session = sessionFactory.getCurrentSession();
        session.remove(orderItem);
    }
}
