package com.oma.services;

import com.oma.dao.DeliveryPointDAO;
import com.oma.model.DeliveryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPointServiceImplementation implements DeliveryPointService{

    @Autowired
    DeliveryPointDAO deliveryPointDAO;

    @Override
    public DeliveryPoint findById(long id) {
        return deliveryPointDAO.findById(id);
    }

    @Override
    public List<DeliveryPoint> getAllDeliveryPoints() {
        return deliveryPointDAO.getAllDeliveryPoints();
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
