package com.oma;

import com.oma.model.Address;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderManagementApplicationTests {

    @Autowired
    private SessionFactory sessionFactory;

    private static Session session;

    @BeforeAll
    public static void cleanDB(){
        clearTable("Address");
        clearTable("Company");
        clearTable("User");
        clearTable("Provider");
        clearTable("ProductsOrder");
        clearTable("Price");
        clearTable( "OrderItem");
        clearTable("DeliveryPoint");
        clearTable("Product");
    }

    private static void clearTable(String tableName) {
        session.beginTransaction();
        session.createQuery("delete " + tableName).executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void simpleOperationOnDB(){
//        given
        Address address = new Address("street");
//        when
        session.beginTransaction();
        session.saveOrUpdate(address);
        session.getTransaction().commit();
        List<Address> addresses = session.createQuery("from Address", Address.class).getResultList();
        session.close();
//        then
        assertTrue(addresses.contains(address));

    }

    @PostConstruct
    private void setSession(){
        session = sessionFactory.openSession();
    }


}

