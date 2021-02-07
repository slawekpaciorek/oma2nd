package com.oma.services;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryPointServiceTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    DeliveryPointService deliveryPointService;

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldFindById() {

        //  given


        //  when


        //  then

    }

    @Test
    void shouldGetAllDeliveryPoints() {

        //  given


        //  when


        //  then

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
}