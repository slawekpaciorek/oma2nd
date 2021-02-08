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

    @Autowired
    AddressService addressService;

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
        if(deliveryPoint.getAddress()!=null){
            addressService.saveAddress(deliveryPoint.getAddress());
        }
        deliveryPointDAO.saveDeliveryPoint(deliveryPoint);
    }

    @Override
    public void updateDeliveryPoint(long id, DeliveryPoint deliveryPoint) {
        DeliveryPoint temp = deliveryPointDAO.findById(id);
        if(deliveryPoint.getAddress()!=null && !(temp.getAddress().equals(deliveryPoint.getAddress()))){
            addressService.updateAddressForId(temp.getAddress().getId(), deliveryPoint.getAddress());
        }
        deliveryPointDAO.updateDeliveryPoint(id, deliveryPoint);
    }

    @Override
    public void removeDeliveryPoint(long id) {
        deliveryPointDAO.removeDeliveryPoint(id);
    }
}
