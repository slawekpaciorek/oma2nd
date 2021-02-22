package com.oma.services;

import com.oma.model.DeliveryPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveryPointService {

    DeliveryPoint findById(long id);
    List<DeliveryPoint> getAllDeliveryPoints();
    void saveDeliveryPoint(DeliveryPoint deliveryPoint);
    void updateDeliveryPoint(long id, DeliveryPoint deliveryPoint);
    void removeDeliveryPoint(long id);
    List<DeliveryPoint> getGetDeliveryPointsForUser(long id);
}
