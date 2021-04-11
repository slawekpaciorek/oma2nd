package com.oma.dao;

import com.oma.model.DeliveryPoint;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryPointDAO {

    DeliveryPoint findById(long id);
    List<DeliveryPoint> getAllDeliveryPoints();
    void saveDeliveryPoint(DeliveryPoint deliveryPoint);
    void updateDeliveryPoint(long id, DeliveryPoint deliveryPoint);
    void removeDeliveryPoint(long id);
    List<DeliveryPoint> getDeliveryPointsForUser(long id);
}
