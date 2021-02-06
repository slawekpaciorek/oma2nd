package com.oma.dao;

import com.oma.model.DeliveryPoint;
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
        return null;
    }

    @Override
    @Transactional
    public List<DeliveryPoint> getAllDeliveryPoints() {
        return null;
    }

    @Override
    @Transactional
    public void saveDeliveryPoint(DeliveryPoint deliveryPoint) {

    }

    @Override
    @Transactional
    public void updateDeliveryPoint(long id, DeliveryPoint deliveryPoint) {

    }

    @Override
    @Transactional
    public void removeDeliveryPoint(long id) {

    }
}
