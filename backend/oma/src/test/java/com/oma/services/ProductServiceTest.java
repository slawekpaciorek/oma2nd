package com.oma.services;

import com.oma.model.Product;
import org.assertj.core.util.Arrays;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    SessionFactory sessionFactory;

    private Session session;

    @BeforeEach
    public void setUp(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Product ").executeUpdate();
        session.getTransaction().commit();
    }

    @AfterEach
    public void cleanUp(){
        session.close();
    }

    @Test
    public void shouldSaveProductInDB(){
//        given
        Product expected = new Product("example", "example_number", "example_number2");
//        then
        productService.saveProduct(expected);
        Product result = (Product)session.createQuery("from Product product where product.name=:productName and  product.catalogId=:catalogId")
                .setParameter("productName", expected.getName())
                .setParameter("catalogId", expected.getCatalogId())
                .getSingleResult();
//        then
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void shouldGetAllProductsFromDB(){
//        given
        List<Product> products = new ArrayList<>();
        products.add(new Product("example1", "trade_1", "cat_1"));
        products.add(new Product("example2", "trade_2", "cat_2"));
        products.add(new Product("example3", "trade_3", "cat_3"));
//        when
        session.beginTransaction();
        for(Product product: products){
            session.saveOrUpdate(product);
        }
        session.getTransaction().commit();
        List<Product> expected = productService.getProducts();
//        then
        Assertions.assertEquals(products, expected);
    }
}
