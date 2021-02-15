package com.oma.dao;

import com.oma.model.Price;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PriceDAOImplementation implements PriceDAO{

    @Autowired
    SessionFactory sessionFactory;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Override
    public Price getPriceById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Price p where p.id = :id", Price.class).getSingleResult();
    }

    @Transactional
    @Override
    public List<Price> getPrices() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Price", Price.class).getResultList();
    }

    @Transactional
    @Override
    public void savePrice(Price price) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(price);
    }

    @Transactional
    @Override
    public void updatePrice(long id, Price price) {
        Price temp = getPriceById(id);
        if(!temp.equals(price)) {
            if(temp.getValue()!=price.getValue())
                temp.setValue(price.getValue());
            Session session = sessionFactory.getCurrentSession();
            session.update(temp);
        }
        else{
            log.info("Objects are the same");
        }
    }

    @Transactional
    @Override
    public void removePrice(long id) {
        Price temp = getPriceById(id);
        Session session = sessionFactory.getCurrentSession();
        session.remove(temp);
    }
}
