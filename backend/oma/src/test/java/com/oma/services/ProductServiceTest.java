package com.oma.services;

import com.oma.model.Product;
import org.assertj.core.util.Arrays;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        for(Product temp : expected){
            Assertions.assertTrue(products.contains(temp));
        }
    }

    @Test
    public void shouldCheckForProductWithID(){
//        given
        Product expected = new Product("example", "trade_example", "cat_example");
//        when
        productService.saveProduct(expected);
        long id = expected.getId();
        Product result = productService.getProductByID(id);
//        then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void shouldUpdateProductWithID(){
//        given
        Product expected = new Product("example", "trade_example", "cat_example");
        Product updatedVersion = new Product("example", "trade_example", "cat_example2");
//        when
        productService.saveProduct(expected);
        long id = expected.getId();
        expected.setCatalogId(updatedVersion.getCatalogId());
        productService.updateProduct(expected);
        Product result = productService.getProductByID(id);
//        then
        Assertions.assertEquals(result, updatedVersion);
    }

    @Test
    public void shouldDeleteProductWithID(){
//        given
        Product product = new Product("example", "trade_ex", "cat_example");
//        when
        productService.saveProduct(product);
        long id = product.getId();
        productService.deleteProduct(id);
//        then
        Assertions.assertThrows(Exception.class, () -> productService.getProductByID(id));
    }

    @Test
    void shouldFindProductWithCatNr() {
        //  given
        Product product = new Product("example", "trade_exa", "cat_exa");
        //  when
        session.save(product);
        //  then
        Assertions.assertEquals(
                productService.getProductByID(product.getId()),
                productService.getProductByCatNumber(product.getCatalogId()));
    }

}
