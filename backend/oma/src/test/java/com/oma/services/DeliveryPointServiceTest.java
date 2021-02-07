package com.oma.services;

import com.oma.model.Address;
import com.oma.model.DeliveryPoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryPointServiceTest {

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
    }

    @Test
    void shouldFindById() {

        //  given
        DeliveryPoint expected = new DeliveryPoint("findById - service", new Address("street", "zipCode", "city"));
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
        DeliveryPoint[] deliveryPoints = {
                new DeliveryPoint("serviceTest1", new Address("street1", "zipcode1", "city1"))
                ,new DeliveryPoint("serviceTest2", new Address("street2", "zipcode2", "city2"))
                ,new DeliveryPoint("serviceTest3", new Address("street3", "zipcode3", "city3"))};
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


        //  when


        //  then

    }

    @Test
    void shouldUpdateDeliveryPoint() {

        //  given


        //  when


        //  then

    }

    @Test
    void shouldRemoveDeliveryPoint() {

        //  given


        //  when


        //  then

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
}