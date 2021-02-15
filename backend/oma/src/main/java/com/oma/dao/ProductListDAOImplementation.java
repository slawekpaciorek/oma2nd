package com.oma.dao;

import com.oma.model.ProductList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductListDAOImplementation implements ProductListDAO {

    @Autowired
    SessionFactory sessionFactory;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Override
    public ProductList getProductFromProductsById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductList p where p.id = :id", ProductList.class).getSingleResult();
    }

    @Transactional
    @Override
    public List<ProductList> getListOfProducts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductList ").getResultList();
    }

    @Transactional
    @Override
    public void saveProductList(ProductList products) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(products);
    }

    @Transactional
    @Override
    public void updateProducts(long id, ProductList products) {
        ProductList productList = getProductFromProductsById(id);
        if(!productList.equals(products)){
            Session session = sessionFactory.getCurrentSession();
            if(productList.getQuantity()!=products.getQuantity())
                productList.setQuantity(products.getQuantity());
            session.update(productList);
        }
        log.info("Objects are the same");
    }

    @Transactional
    @Override
    public void removeProducts(long id) {
        ProductList productList = getProductFromProductsById(id);
        Session session = sessionFactory.getCurrentSession();
        session.remove(productList);
    }
}
