package com.oma.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oma.model.Company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oma.model.Product;
import com.oma.services.CompanyService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @BeforeEach
    void setUp() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Company").executeUpdate();
        session.getTransaction().commit();
    }

    @AfterEach
    void cleanUp() {
        session.close();
    }


    @Test
    void shouldReturnOkForCompanyList() throws Exception {

        //  given

        List<Company> companies = companyService.getDefaultCompanies();
        String uri = "/company/all-companies";
        //  when
        for(Company company : companies){
            companyService.saveCompany(company);
        }
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = result.getResponse().getContentAsString();
        Company[] resultData = mapFromJson(content, Company[].class);
        //  then

        assertEquals(200, result.getResponse().getStatus());
        for (Company company : resultData){
            assertTrue(companies.contains(company));
        }

    }

    private <T> T mapFromJson(String content, Class<T> resultClass) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, resultClass);
    }

}

