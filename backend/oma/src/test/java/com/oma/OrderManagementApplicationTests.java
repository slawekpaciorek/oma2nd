package com.oma;

import com.oma.model.Address;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OrderManagementApplicationTests {

    @Autowired
    SessionFactory sessionFactory;

    @BeforeEach
    public void cleanUp(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Address ").executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void simpleOperationOnDB(){
//        given
        Address address = new Address("street");
        Session session = sessionFactory.openSession();
//        when
        session.beginTransaction();
        session.saveOrUpdate(address);
        session.getTransaction().commit();
        List<Address> addresses = session.createQuery("from Address", Address.class).getResultList();
        session.close();
//        then
        assertTrue(addresses.contains(address));

    }
}

