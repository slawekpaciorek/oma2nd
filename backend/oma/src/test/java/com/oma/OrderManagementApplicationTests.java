//package com.oma;
//
//import com.oma.model.Address;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@SpringBootTest
//class OrderManagementApplicationTests {
//
//    @Autowired
//    SessionFactory sessionFactory;
//
//    @Test
//    public void simpleOperationOnDB(){
////        given
//        Address address = new Address("street", "zipcode", "city", 900900900);
//        Session session = sessionFactory.openSession();
////        when
//        session.beginTransaction();
//        session.saveOrUpdate(address);
//        session.getTransaction().commit();
//        Address result = session.createQuery("from Address a where a.zipCode = :zipCode and a.streetNameAndNumber = :streetNumber",Address.class)
//                .setParameter("zipCode",address.getZipCode())
//                .setParameter("streetNumber", address.getStreetNameAndNumber())
//                .getSingleResult();
//        session.close();
////        then
//        assertEquals(result, address, "Address was save and read from db");
//
//    }
//
//}
