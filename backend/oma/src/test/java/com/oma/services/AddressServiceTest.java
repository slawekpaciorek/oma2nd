package com.oma.services;

import com.oma.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    public AddressService addressService;

    @Autowired
    public SessionFactory sessionFactory;

    private Session session;

    @BeforeEach
    public void cleanDB(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Address ").executeUpdate();
        session.getTransaction().commit();
    }

    @AfterEach
    public void cleanAfter(){
        session.close();
    }

    @Test
    public void shouldGetAllAddressesFromDB(){
//        given
        Address[] expected = { new Address("address1"), new Address("address2"), new Address("address3")};
//        when
        for(Address address : expected){
            session.beginTransaction();
            session.save(address);
            session.getTransaction().commit();
        }
        Address[]result = new Address[expected.length];
        result = addressService.getAllAddresses()
                .stream()
                .sorted(Comparator.comparingLong(Address::getId))
                .collect(Collectors.toList())
                .toArray(result);

//        then
        assertArrayEquals(expected, result, "AddressService get all addresses from DB");
    }
}
