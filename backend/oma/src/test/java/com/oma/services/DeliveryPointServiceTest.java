package com.oma.services;

import com.oma.model.Address;
import com.oma.model.DeliveryPoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryPointServiceTest {

    private int counter = 0;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    DeliveryPointService deliveryPointService;

    @AfterEach
    void tearDown() {
        Session session = getSession();
        beginTransaction(session);
        session.createQuery("delete Address ").executeUpdate();
        commitTransaction(session);
        beginTransaction(session);
        session.createQuery("delete DeliveryPoint").executeUpdate();
        commitTransaction(session);
        closeSession(session);
        counter = 0;
    }

    @Test
    void shouldFindById() {

        //  given
        DeliveryPoint expected = getDefaultDeliveryPoint();
        Session session = getSession();

        //  when
        beginTransaction(session);
        session.saveOrUpdate(expected.getAddress());
        commitTransaction(session);
        beginTransaction(session);
        session.saveOrUpdate(expected);
        commitTransaction(session);
        closeSession(session);
        DeliveryPoint result = deliveryPointService.findById(expected.getId());

        //  then
        assertEquals(expected, result);

    }

    @Test
    void shouldGetAllDeliveryPoints() {

        //  given
        DeliveryPoint[] deliveryPoints = new DeliveryPoint[3];
        for(int i = 0; i < deliveryPoints.length; i++) {
            deliveryPoints[i] = getDefaultDeliveryPoint();
        }
        Session session = getSession();

        //  when
        for(DeliveryPoint temp : deliveryPoints){
            beginTransaction(session);
            session.saveOrUpdate(temp.getAddress());
            commitTransaction(session);
            beginTransaction(session);
            session.saveOrUpdate(temp);
            commitTransaction(session);
        }
        closeSession(session);
        DeliveryPoint[] result = deliveryPointService.getAllDeliveryPoints().toArray(DeliveryPoint[]::new);

        //  then
        assertArrayEquals(result, deliveryPoints);

    }

    @Test
    void shouldSaveDeliveryPoint() {

        //  given
        DeliveryPoint expected = getDefaultDeliveryPoint();

        //  when
        deliveryPointService.saveDeliveryPoint(expected);
        List<DeliveryPoint> resultList = deliveryPointService.getAllDeliveryPoints();

        //  then
        assertTrue(resultList.contains(expected));

    }

    @Test
    void shouldUpdateDeliveryPoint() {

        //  given
        DeliveryPoint deliveryPoint = getDefaultDeliveryPoint();
        DeliveryPoint update = getDefaultDeliveryPoint();

        //  when
        deliveryPointService.saveDeliveryPoint(deliveryPoint);
        long id = deliveryPoint.getId();
        deliveryPointService.updateDeliveryPoint(id, update);
        DeliveryPoint result = deliveryPointService.findById(id);

        //  then
        assertEquals(update, result);

    }

    @Test
    void shouldRemoveDeliveryPoint() {

        //  given
        DeliveryPoint deliveryPoint = getDefaultDeliveryPoint();

        //  when
        deliveryPointService.saveDeliveryPoint(deliveryPoint);
        long id = deliveryPoint.getId();
        deliveryPointService.removeDeliveryPoint(id);

        //  then
        assertThrows(EmptyResultDataAccessException.class, () -> deliveryPointService.findById(id));

    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    private void closeSession(Session session) {
        session.close();
    }

    private void commitTransaction(Session session) {
        session.getTransaction().commit();
    }

    private void beginTransaction(Session session) {
        session.beginTransaction();
    }

    private Address getDefaultAddress(){
        return new Address("defaultStreet" + counter, "defaultZipCode" + counter, "defaultCity" +counter);
    }

    private DeliveryPoint getDefaultDeliveryPoint(){
        DeliveryPoint deliveryPoint = new DeliveryPoint("defaultDPName" + counter, getDefaultAddress());
        counter++;
        return deliveryPoint;
    }
}