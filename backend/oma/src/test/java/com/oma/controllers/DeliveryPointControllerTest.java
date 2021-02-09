package com.oma.controllers;

import com.oma.model.Address;
import com.oma.model.DeliveryPoint;
import com.oma.services.DeliveryPointService;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryPointControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DeliveryPointService deliveryPointService;

    private int expectedStatus = 200;
    private int counter = 0;
    private Session session;
    private SessionFactory sessionFactory;

    @AfterEach
    void tearDown() {
        counter = 0;
        session = sessionFactory.openSession();
        resetAddressTable(session);
        resetDeliveryPointTable(session);
        session.close();
    }


    @Test
    void shouldDisplayAllDeliveryPoints() {

        //  given


        //  when


        //  then


    }

    @Test
    void shouldAddDeliveryPoint() {

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

    private DeliveryPoint returnDefaultDeliveryPoint(){
        DeliveryPoint deliveryPoint = new DeliveryPoint(
                "defaultDeliveryPoint" + counter,
                new Address("defaultStreetName" + counter, "defaultZipCode" + counter, "defaultCity" + counter)
        );
        counter++;
        return deliveryPoint;
    }

    private void resetDeliveryPointTable(Session session) {
        session.beginTransaction();
        session.createQuery("delete DeliveryPoint").executeUpdate();
        session.getTransaction().commit();
    }

    private void resetAddressTable(Session session) {
        session.beginTransaction();
        session.createQuery("delete Address ").executeUpdate();
        session.getTransaction().commit();
    }
}