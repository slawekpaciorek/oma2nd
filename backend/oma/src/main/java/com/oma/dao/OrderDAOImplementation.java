package com.oma.dao;

import com.oma.model.ProductsOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDAOImplementation implements OrderDao {

    @Autowired
    SessionFactory sessionFactory;

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Transactional
    @Override
    public ProductsOrder getOrderById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductsOrder o where o.id = :id", ProductsOrder.class).setParameter("id", id).getSingleResult();
    }

    @Transactional
    @Override
    public List<ProductsOrder> getOrderList() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductsOrder", ProductsOrder.class).getResultList();
    }

    @Transactional
    @Override
    public void saveOrder(ProductsOrder order) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);
    }

    @Transactional
    @Override
    public void updateOrder(long id, ProductsOrder order) {
        ProductsOrder orderToUpdate = getOrderById(id);
        if(!orderToUpdate.equals(order)){ ;
            if(!orderToUpdate.getStatus().equals(order.getStatus()))
                orderToUpdate.setStatus(order.getStatus());
            if(!orderToUpdate.getInfo().equals(order.getInfo()))
                orderToUpdate.setInfo(order.getInfo());
            if(orderToUpdate.getSummaryValue()!=order.getSummaryValue())
                orderToUpdate.setSummaryValue(order.getSummaryValue());
            if(!orderToUpdate.getCreatedAt().equals(order.getCreatedAt()))
                orderToUpdate.setCreatedAt(order.getCreatedAt());
            Session session = sessionFactory.getCurrentSession();
            session.update(orderToUpdate);
        }
        else{
            log.info("Objects are the same");
        }
    }

    @Transactional
    @Override
    public void removeOrder(long id){
        ProductsOrder order = getOrderById(id);
        Session session = sessionFactory.getCurrentSession();
        session.remove(order);
    }
}
