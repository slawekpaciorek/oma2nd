package com.oma.services;

import java.util.Collection;
import java.util.List;
import com.oma.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void shouldGetEmptyListWithAddressesFromDB(){
//        when
        List<Address> result = addressService.getAllAddresses();
//        then
        assertEquals(result, Collections.EMPTY_LIST);
    }

    @Test
    public void shouldSaveAddressInDB(){
//        given
        Address address = new Address("streetExample","zipCodeExample", "cityEample", 900900900);
//        when
        addressService.saveAddress(address);
        List<Address> addresses = addressService.getAllAddresses();
//        then
        assertTrue(addresses.contains(address));
    }

    @Test
    public void shouldGetAddressFromDBForId(){
//        given
        Address address = new Address("example","zipcode","city", 900900900);
//        when
        addressService.saveAddress(address);
        long id = address.getId();
        Address result = addressService.getAddressForId(id);
//        then
        assertEquals(address, result);
    }

    @Test
    public void shouldUpdateAddressWithId(){
//        given
        Address input = new Address("Basic Input");
        Address update = new Address("Updated Input");
//        when
        addressService.saveAddress(input);
        addressService.updateAddressForId(input.getId(), update);
        Address expected = addressService.getAddressForId(input.getId());
//        then
        assertEquals(expected.getStreetNameAndNumber(), update.getStreetNameAndNumber());
    }
}
