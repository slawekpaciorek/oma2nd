package com.oma.controllers;

import com.oma.services.DeliveryPointService;
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

    @AfterEach
    void tearDown() {
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
}