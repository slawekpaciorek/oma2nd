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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    private int expectedStatus = 200;

    @BeforeEach
    void setUp() {
        user = new User("userName", "userName@user", "operator", 900900900);
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Company").executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete Address").executeUpdate();
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
        userService.addUser(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/list")
                .accept("application/json")).andReturn();
        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        User[] userList = mapFromJson(content, User[].class);
//        then
        assertEquals(status, expectedStatus);
        assertTrue(Arrays.asList(userList).contains(user));
    }

    @Test
    @Transactional
    void shouldAddUser() throws Exception {
//        when
        user.setCompany(returnTestCompany());
        String body = mapObjectToJson(user);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .contentType("application/json")
                .content(body))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        List<User> users = userService.getAllUser();
//        then
        assertEquals(status, expectedStatus);
        assertTrue(users.contains(user));
    }

    @Test
    void shouldUpdateUser() throws Exception {
//        given
        User update = new User("updateName", "updateUserName", "operator", 900100100);
//        when
        user.setCompany(returnTestCompany());
        userService.addUser(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/users/update")
                .contentType("application/json")
                .content(mapObjectToJson(update))
                .param("id", String.valueOf(user.getId())))
                .andReturn();
        int status = result.getResponse().getStatus();
        User resultUser = userService.findUserById(user.getId());
//        then
        assertTrue(resultUser.equals(update));
        assertEquals(status, expectedStatus);
    }

    @Test
    void shouldRemoveUser() throws Exception {
        user.setCompany(returnTestCompany());
        userService.addUser(user);
        long id = user.getId();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/users/remove")
                .param("id", String.valueOf(id))
                .contentType("application/json"))
                .andReturn();
        int status = result.getResponse().getStatus();

        assertEquals(expectedStatus, status);
        assertThrows(EmptyResultDataAccessException.class, ()->userService.findUserById(id));
    }

    private <T> T mapFromJson(String content, Class<T> resultClass) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, resultClass);
    }

    private String mapObjectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private Company returnTestCompany(){
        return companyService.getAllWithAddresses().get(0);
    }
}