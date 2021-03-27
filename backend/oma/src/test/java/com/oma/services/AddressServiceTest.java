package com.oma.services;

import com.oma.model.Address;
import com.oma.utils.DBCleaner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
        DBCleaner dbCleaner = new DBCleaner();
        dbCleaner.setSessionFactory(sessionFactory);
        dbCleaner.setTableNames(new String[]{"Address"});
        dbCleaner.cleanDB();
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
                .sorted(Comparator.comparingLong(value -> value.getId()))
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
        Address address = new Address("Example");
//        when
        addressService.saveAddress(address);
        List<Address> addresses = addressService.getAllAddresses();
//        then
        assertTrue(addresses.contains(address));
    }

    @Test
    public void shouldGetAddressFromDBForId(){
//        given
        Address address = new Address("example");
//        when
        addressService.saveAddress(address);
        long id = address.getId();
        Address result = addressService.getAddressForId(id);
//        then
        assertEquals(address, result);
    }

    @Test
    public void shouldCatchExceptionForIncorrectIdNumber(){
//        given
        long id= addressService.getAllAddresses().stream().mapToLong(Address::getId).sum();
//        then
        assertThrows(Exception.class, ()->addressService.getAddressForId(id));

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
        assertEquals(expected.getClass(), update.getClass());
    }
    @Test
    public void shouldRemoveAddressWithIdFromDB(){
//        given
        Address addressToRemove = new Address("AddressToRemove");
//        when
        addressService.saveAddress(addressToRemove);
        long id = addressToRemove.getId();
        addressService.removeAddressWithId(id);
//        then
        assertThrows(Exception.class, ()-> addressService.getAddressForId(id));
    }
}
