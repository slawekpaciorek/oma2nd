package com.oma.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oma.model.Product;
import com.oma.services.ProductService;
import com.oma.utils.DBCleaner;
import net.bytebuddy.utility.RandomString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
//@CrossOrigin({"localhost:4200", "localhost:5550"})
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    ProductService productService;

    private Session session;
    private int expectedStatus = 200;

    @BeforeEach
    public void setUp(){
        cleanDB();
    }

    @Test
    @WithMockUser("user")
    public void shouldDisplayListOfProducts() throws Exception {

        //        given
        List<Product> expected = generateListOfProducts();

        //        when
        expected.forEach(x -> productService.saveProduct(x));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/products/all")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Product[] products = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Product[].class);

        //        then
        assertEquals(status, expectedStatus);
        for(Product temp : products){
            assertTrue(expected.contains(temp));
        }

    }

    @Test
    @WithMockUser("user")
    public void shouldSaveProduct() throws Exception {

        //        given
        Product product = generateProduct(0);
        String jsonBody = new ObjectMapper().writeValueAsString(product);

        //        when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/products/add")
                .contentType("application/json")
                .content(jsonBody))
                .andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();
        List<Product> resultList = productService.getProducts();

        //        then
        assertEquals(expectedStatus, resultStatus);
        assertTrue(resultList.contains(product));
    }

    @Test
    @WithMockUser("user")
    public void shouldUpdateProduct() throws Exception {

        //        given
        Product product = generateProduct(0);
        Product expected = generateProduct(1);
        String jsonBody = new ObjectMapper().writeValueAsString(expected);

        //        when
        productService.saveProduct(product);
        long id = product.getId();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/products/update")
                .contentType("application/json")
                .content(jsonBody)
                .param("id", String.valueOf(id)))
                .andReturn();
        Product result = productService.getProductByID(id);
        int resultStatus = mvcResult.getResponse().getStatus();

        //        then
        assertEquals(resultStatus, expectedStatus);
        assertEquals(result, expected);
    }

    @Test
    @WithMockUser("user")
    public void shouldRemoveProduct() throws Exception {

        //        given
        Product product = generateProduct(0);

        //        when
        productService.saveProduct(product);
        long id = product.getId();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/products/remove")
                .param("id", String.valueOf(id)))
                .andReturn();

        int resultStatus = result.getResponse().getStatus();

        //        then
        assertEquals(resultStatus, expectedStatus);
        assertThrows(EmptyResultDataAccessException.class, () -> productService.getProductByID(id));
    }

    private void cleanDB() {
        DBCleaner dbCleaner = new DBCleaner();
        dbCleaner.setSessionFactory(sessionFactory);
        dbCleaner.setTableNames(new String[]{"Product"});
        dbCleaner.cleanDB();
    }

    private void startTransaction(Session session) {
        session.beginTransaction();
    }

    private void commitTransaction(Session session) {
        session.getTransaction().commit();
    }

    private Session returnSession() {
        return sessionFactory.openSession();
    }

    private List<Product> generateListOfProducts(){

        List<Product> tempList = new ArrayList<>();
        int random = new Random().nextInt(10);
        int counter = 0;

        for(int i = 0; i < random;i ++){
            counter++;
            tempList.add(generateProduct(counter));
        }
        return tempList;
    }

    private Product generateProduct(int counter) {
        return new Product(counter + " .Product - " + hashCode(),
                String.valueOf(hashCode()),
                String.valueOf(hashCode()),
                new RandomString().nextString());
    }
}
