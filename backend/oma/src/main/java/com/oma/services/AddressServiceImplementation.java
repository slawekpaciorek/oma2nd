package com.oma.services;

import com.oma.dao.AddressDAO;
import com.oma.model.Address;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {

    @Autowired
    AddressDAO addressDAO;

    private final Logger log = LoggerFactory.getLogger(AddressServiceImplementation.class);

    @Override
    @Transactional
    public List<Address> getAllAddresses() {
        log.trace("Get all Addressess.");
        return addressDAO.getAllAddresses();
    }

    @Override
    @Transactional
    public void saveAddress(Address address) {
        log.trace("Save Address.");
        addressDAO.saveAddress(address);
    }

    @Override
    @Transactional
    public Address getAddressForId(long id){
        log.trace("Get Address for ID.");
        return addressDAO.getAddressForId(id);
    }

    @Override
    @Transactional
    public void updateAddressForId(long id, Address address) {
        log.trace("Update Address for ID.");
        addressDAO.updateAddress(id, address);
    }

    @Override
    @Transactional
    public void removeAddressWithId(long id) {
        log.trace("Remove Address with ID.");
        addressDAO.removeAddress(id);
    }
}
