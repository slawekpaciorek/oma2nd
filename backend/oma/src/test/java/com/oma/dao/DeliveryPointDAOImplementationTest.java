package com.oma.dao;

import com.oma.model.Address;
import com.oma.model.DeliveryPoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryPointDAOImplementationTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    DeliveryPointDAOImplementation deliveryPointDAOImplementation;

    @AfterEach
    void tearDown() {
        Session session = getSession();
        startTransaction(session);
        session.createQuery("delete DeliveryPoint ");
        commitTransaction(session);
        closeSession(session);
    }

    @Test
    void shouldFindById() {
//        given
        DeliveryPoint deliveryPoint = new DeliveryPoint("testName");
        Session session = getSession();
//        when
        startTransaction(session);
        session.saveOrUpdate(deliveryPoint);
        commitTransaction(session);
        closeSession(session);
        long id = deliveryPoint.getId();
        DeliveryPoint result = deliveryPointDAOImplementation.findById(id);
//        then
        assertEquals(deliveryPoint, result);
    }

    @Test
    void shouldGetAllDeliveryPoints() {
//        given
        List<DeliveryPoint> deliveryPoints = new ArrayList<>();
        DeliveryPoint temp1 = new DeliveryPoint("TestName1");
        DeliveryPoint temp2 = new DeliveryPoint("TestName2");
        DeliveryPoint temp3 = new DeliveryPoint("TestName3");
        Session session = getSession();
//        when
        deliveryPoints.add(temp1);
        deliveryPoints.add(temp3);
        deliveryPoints.add(temp2);
        for(DeliveryPoint deliveryPoint : deliveryPoints){
            startTransaction(session);
            session.saveOrUpdate(deliveryPoint);
            commitTransaction(session);
        };
        closeSession(session);
        List<DeliveryPoint> result = deliveryPointDAOImplementation.getAllDeliveryPoints();
//        then
        result.forEach(x-> assertTrue(deliveryPoints.contains(x)));
    }

    @Test
    void shouldSaveDeliveryPoint() {
//        given
        DeliveryPoint deliveryPoint = new DeliveryPoint("savingDPTest");
//        when
        deliveryPointDAOImplementation.saveDeliveryPoint(deliveryPoint);
        List<DeliveryPoint> resultList = deliveryPointDAOImplementation.getAllDeliveryPoints();
//        then
        assertTrue(resultList.contains(deliveryPoint));

    }

    @Test
    void shouldUpdateDeliveryPoint() {
//        given
        DeliveryPoint deliveryPoint = new DeliveryPoint("updateDPTest");
        DeliveryPoint expected = new DeliveryPoint("expectedAfterUpdate");
//        when
        deliveryPointDAOImplementation.saveDeliveryPoint(deliveryPoint);
        long id = deliveryPoint.getId();
        deliveryPointDAOImplementation.updateDeliveryPoint(id, expected);
        DeliveryPoint result = deliveryPointDAOImplementation.findById(id);
//        then
        assertEquals(expected, result);
    }

    @Test
    void shouldRemoveDeliveryPointFromDB(){
//        given
        DeliveryPoint deliveryPoint = new DeliveryPoint("removeDeliveryPointTest");
//        when
        deliveryPointDAOImplementation.saveDeliveryPoint(deliveryPoint);
        long id = deliveryPoint.getId();
        deliveryPointDAOImplementation.removeDeliveryPoint(id);
//        then
        assertThrows(EmptyResultDataAccessException.class, () -> deliveryPointDAOImplementation.findById(id));

    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    private void closeSession(Session session){
        session.close();
    }

    private void startTransaction(Session session){
        session.beginTransaction();
    }

    private void commitTransaction(Session session){
        session.getTransaction().commit();
    }
}