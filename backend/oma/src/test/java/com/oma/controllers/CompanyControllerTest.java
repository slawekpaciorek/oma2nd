package com.oma.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oma.model.Address;
import com.oma.model.Company;
import com.oma.model.User;
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

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void shouldSaveSimpleCompanyInDB()throws Exception{
//        given
        Company company = new Company("CompanyName", "taxIdNumber", new Address());
        String uri = "/company/add";
//        when
        String jsonBody = mapCompanyToJson(company);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(jsonBody)
                .contentType("application/json"))
                .andReturn();
        int status = result.getResponse().getStatus();
//        then
        assertEquals(status, 200);

        Company expected = companyService.getAllCompany()
                .stream().filter(x->x.getName().equals(company.getName())).findAny().get();
        assertTrue(company.equals(expected));
    }

    @Test
    public void shouldSaveCompanyWithUserAndAddress() throws Exception {
//        given
        Company company = new Company("ComapnyNameTest", "testNIP", new Address("testStreet", "testCode", "testCity"));
        User user = new User("name","username","manager",900900900);
        company.addUser(user);
        String jsonBody = mapCompanyToJson(company);
//        when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/company/add")
                .content(jsonBody)
                .contentType("application/json"))
                .andReturn();
        int status = result.getResponse().getStatus();
//        then
        assertEquals(status, 200);

    }

    @Test
    public void shouldUpdateCompanyWithNewData() throws Exception{
//        given
        Company company = new Company("ComapnyNameTest", "testNIP", new Address("testStreet", "testCode", "testCity"));
        Company updateCompany = new Company("UpdateCompanyName", "updatedNIP", new Address("updatedStreet", "updatedCode", "updatedCity" ));
//        when
        companyService.saveCompany(company);
        String id = Long.toString(company.getId());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/company/update")
                .content(mapCompanyToJson(updateCompany))
                .contentType("application/json")
                .param("id", id))
                .andReturn();
        int status = result.getResponse().getStatus();
//        then
        assertEquals(status, 200);
        assertEquals(updateCompany, companyService.getCompanyById(company.getId()));

    }

    private <T> T mapFromJson(String content, Class<T> resultClass) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, resultClass);
    }

    private String mapCompanyToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}

