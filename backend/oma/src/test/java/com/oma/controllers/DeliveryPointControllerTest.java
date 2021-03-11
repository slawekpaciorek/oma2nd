package com.oma.controllers;

import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oma.model.Address;
import com.oma.model.Company;
import com.oma.model.User;
import com.oma.model.DeliveryPoint;
import com.oma.services.*;
import com.oma.utils.DBCleaner;
import net.bytebuddy.utility.RandomString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryPointControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DeliveryPointService deliveryPointService;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @Autowired
    private SessionFactory sessionFactory;

    private int expectedStatus = 200;
    private int counter = 0;
    private Session session;


    @BeforeEach
    void tearDown() {
        counter = 0;
        cleanDB();
        Company company = new Company(getDefaultString(), getDefaultString(), new Address(getDefaultString(), getDefaultString(), getDefaultString()));
        User user = new User(getDefaultString(),getDefaultString(),"manager", 900900900);
        company.addUser(user);
        companyService.saveCompany(company);
    }


    @Test
    void shouldDisplayAllDeliveryPoints() throws Exception {
//      given
        List<User> users = userService.getAllUser();
        //  when
        DeliveryPoint[] deliveryPoints = new DeliveryPoint[3];
        for(int i = 0; i < deliveryPoints.length; i++){
            deliveryPoints[i] = returnDefaultDeliveryPoint();
            deliveryPoints[i].setCreatedBy(users.get(Math.abs(new Random().nextInt(users.size()))));
            deliveryPointService.saveDeliveryPoint(deliveryPoints[i]);
        }
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/delivery-points/list")
                .accept("application/json")).andReturn();
        int status = result.getResponse().getStatus();
        DeliveryPoint[] resultList = mapFromJson(result.getResponse().getContentAsString(), DeliveryPoint[].class);

        //  then
        assertEquals(expectedStatus, status);
        assertArrayEquals(deliveryPoints, resultList);


    }

    @Test
    void shouldAddDeliveryPoint() throws Exception {

        //  given
        DeliveryPoint deliveryPoint = returnDefaultDeliveryPoint();
        String jsonBody = mapObjectToJson(deliveryPoint);

        //  when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/delivery-points/add")
                .contentType("application/json")
                .content(jsonBody)).andReturn();
        int status = result.getResponse().getStatus();

        //  then
        assertEquals(status, expectedStatus);


    }

    @Test
    void shouldUpdateDeliveryPoint() throws Exception {

        //  given
        DeliveryPoint deliveryPoint = returnDefaultDeliveryPoint();
        DeliveryPoint expectedDeliveryPoint = returnDefaultDeliveryPoint();

        //  when
        deliveryPointService.saveDeliveryPoint(deliveryPoint);
        long id = deliveryPoint.getId();
        String jsonBody = mapObjectToJson(expectedDeliveryPoint);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/delivery-points/update")
                .param("id", String.valueOf(id))
                .contentType("application/json")
                .content(jsonBody))
                .andReturn();
        DeliveryPoint resultDeliveryPoint = deliveryPointService.findById(id);
        int resultStatus = mvcResult.getResponse().getStatus();

        //  then
        assertEquals(expectedStatus, resultStatus);
        assertEquals(expectedDeliveryPoint, resultDeliveryPoint);


    }

    @Test
    void shouldRemoveDeliveryPoint() throws Exception {

        //  given
        DeliveryPoint deliveryPoint = returnDefaultDeliveryPoint();

        //  when
        deliveryPointService.saveDeliveryPoint(deliveryPoint);
        long id = deliveryPoint.getId();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/delivery-points/remove")
                .param("id", String.valueOf(id)))
                .andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();

        //  then
        assertEquals(resultStatus, expectedStatus);
        assertThrows(EmptyResultDataAccessException.class, () -> deliveryPointService.findById(id));

    }

    private DeliveryPoint returnDefaultDeliveryPoint(){
        DeliveryPoint deliveryPoint = new DeliveryPoint(
                "defaultDeliveryPoint" + counter,
                new Address("defaultStreetName" + counter, "defaultZipCode" + counter, "defaultCity" + counter)
        );
        counter++;
        return deliveryPoint;
    }

    private <T>T mapFromJson(String content, Class<T> resultClass) throws JsonParseException, JsonMappingException, IOException {
        return new ObjectMapper().readValue(content, resultClass);
    }

    private String mapObjectToJson(Object deliveryPoint) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(deliveryPoint);
    }

    private String getDefaultString() {
        return new RandomString().nextString();
    }

    private void cleanDB() {
        DBCleaner dbCleaner = new DBCleaner();
        dbCleaner.setSessionFactory(sessionFactory);
        dbCleaner.setTableNames(new String[]{"Company", "User", "DeliveryPoint", "Address"});
        dbCleaner.cleanDB();
    }
}