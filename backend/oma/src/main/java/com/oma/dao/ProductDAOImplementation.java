package com.oma.dao;

import com.oma.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductDAOImplementation implements ProductDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProductDAOImplementation.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Product product) {
        logger.info("Trying to save the product to the database from the repositories layer!");
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
    }

    @Override
    public List<Product> getAll() {
        logger.warn("Exposing all the product list from repositories layer!");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getByID(long id) {
        logger.info("Trying find product for id from the repositories layer!");
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.createQuery("from Product product where product.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void remove(long id) {
        logger.warn("Trying remove product from the repositories layer!");
        Session session = sessionFactory.getCurrentSession();
        session.remove(getByID(id));
    }

    @Override
    public Product getByCatNumber(String catalogId) {
        logger.warn("Trying get product by catalog number from the repositories layer!");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product p where p.catalogId = :catNumber",Product.class)
                .setParameter("catNumber", catalogId)
                .getSingleResult();
    }
}
