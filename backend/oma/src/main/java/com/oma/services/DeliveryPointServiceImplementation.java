package com.oma.services;

import com.oma.dao.DeliveryPointDAO;
import com.oma.model.DeliveryPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeliveryPointServiceImplementation implements DeliveryPointService{

    @Autowired
    DeliveryPointDAO deliveryPointDAO;

    @Override
    public DeliveryPoint findById(long id) {
        return null;
    }

    @Override
    public List<DeliveryPoint> getAllDeliveryPoints() {
        return null;
    }

    @Override
    public void saveDeliveryPoint(DeliveryPoint deliveryPoint) {

    }

    @Override
    public void updateDeliveryPoint(long id, DeliveryPoint deliveryPoint) {

    }

    @Override
    public void removeDeliveryPoint(long id) {

    }
}
