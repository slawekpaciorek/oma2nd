package com.oma.dao;

import com.oma.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductDAOImplementationTest {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    SessionFactory sessionFactory;

    private Session session;

    @AfterEach
    public void tearDown(){
        cleanDB();
    }

    @Test
    public void shouldSaveProductInDB(){

//        given
        Product product = randomProduct();

//        when
        productDAO.save(product);
        long id = product.getId();
        session = returnSession();
        startTransaction(session);
        List<Product> resultList = session.createQuery("from Product").getResultList();
        commitTransaction(session);
        session.close();

//        then
        assertTrue(resultList.contains(product));
    }

    @Test
    public void shouldFindProductById(){

//        given
        Product product = randomProduct();

//        when
        productDAO.save(product);
        long id = product.getId();
        Product result = productDAO.getByID(id);

//        then
        assertEquals(product, result);
    }

    @Test
    public void shouldDisplayListOfProducts(){

//        given
        Product[] products = new Product[5];

//        when
        for(int i = 0 ; i < products.length; i++){
            products[i]= randomProduct();
            productDAO.save(products[i]);
        }

        List<Product> resultList = productDAO.getAll();

//        then
        for(int i = 0; i < products.length; i++){
                assertTrue(resultList.contains(products[i]));
        }

    }

    @Test
    public void shouldUpdateProduct(){

//        given
        Product product = randomProduct();

//        when
        productDAO.save(product);
        long id = product.getId();
        Product update = randomProduct();
        update.setTradeId("updateTradeId");
        productDAO.update(id, update);
        Product result = productDAO.getByID(id);

//        then
        assertEquals(update, result);
    }

    @Test
    public void shouldRemoveProductFromDB(){

//        given
        Product product = randomProduct();

//        when
        productDAO.save(product);
        long id = product.getId();
        productDAO.remove(id);

//        then
       assertThrows(EmptyResultDataAccessException.class, () -> productDAO.getByID(id));
    }

    private Session returnSession(){
        return sessionFactory.openSession();
    }

    private void startTransaction(Session session){
        session.beginTransaction();
    }

    private void commitTransaction(Session session){
        session.getTransaction().commit();
    }

    private void cleanDB(){
        session = returnSession();
        startTransaction(session);
        session.createQuery("delete Product").executeUpdate();
        commitTransaction(session);
        session.close();
    }

    private Product randomProduct(){
        return new Product("Product-" + hashCode(),String.valueOf(hashCode()),String.valueOf(hashCode()), "category");
    }

}
