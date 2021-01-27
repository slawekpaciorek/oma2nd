package com.oma.controllers;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oma.model.Address;
import com.oma.model.Company;
import com.oma.model.User;
import com.oma.services.CompanyService;
import com.oma.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.CockroachDB201Dialect;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    private Session session;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("userName", "userName@user", "operator", 900900900);
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Company").executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
//        session.getTransaction().commit();
        companyService.saveCompany(
                new Company("companyUserTest", "000000000", new Address("streetUserTest","zipCodeUserTest", "cityUserTest"))
        );
    }

    @Test
    void shouldDisplayUsers() throws Exception {
//        when
        Company company = companyService.getAllWithAddresses().get(0);
        user.setCompany(company);
        userService.addUser(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/list")
                .accept("application/json")).andReturn();
        int status = result.getResponse().getStatus();
//        then
        assertEquals(status, 200);
    }

    @Test
    void shouldAddUser() {
    }

    @Test
    void schouldUpdateUser() {
    }

    @Test
    void schouldRemoveUser() {
    }

    private <T> T mapFromJson(String content, Class<T> resultClass) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, resultClass);
    }

    private String mapObjectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}