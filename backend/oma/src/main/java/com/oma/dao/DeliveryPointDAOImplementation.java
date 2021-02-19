package com.oma.dao;

import com.oma.model.DeliveryPoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DeliveryPointDAOImplementation implements DeliveryPointDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public DeliveryPoint findById(long id) {
        Session session = getSession();
        DeliveryPoint deliveryPoint = (DeliveryPoint) session.createQuery("from DeliveryPoint deliveryPoint where deliveryPoint.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return deliveryPoint;
    }

    @Override
    @Transactional
    public List<DeliveryPoint> getAllDeliveryPoints() {
        Session session = getSession();
        return session.createQuery("from DeliveryPoint ").getResultList();
    }

    @Override
    @Transactional
    public void saveDeliveryPoint(DeliveryPoint deliveryPoint) {
        Session session = getSession();
        session.saveOrUpdate(deliveryPoint);
    }

    @Override
    @Transactional
    public void updateDeliveryPoint(long id, DeliveryPoint deliveryPoint) {
        Session session = getSession();
        DeliveryPoint temp = findById(id);
        if(!deliveryPoint.getName().equals(temp.getName()))
            temp.setName(deliveryPoint.getName());
        session.update(temp);
    }

    @Override
    @Transactional
    public void removeDeliveryPoint(long id) {
        Session session = getSession();
        DeliveryPoint temp = findById(id);
        session.remove(temp);
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
