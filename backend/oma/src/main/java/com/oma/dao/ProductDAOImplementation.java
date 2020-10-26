package com.oma.dao;

import com.oma.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductDAOImplementation implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
    }

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.createQuery("from Product product where product.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void remove(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(getByID(id));
    }

    @Override
    public Product getByCatNumber(String catalogId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product p where p.catalogId = :catNumber",Product.class)
                .setParameter("catNumber", catalogId)
                .getSingleResult();
    }
}
