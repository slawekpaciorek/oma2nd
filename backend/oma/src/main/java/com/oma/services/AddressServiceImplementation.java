package com.oma.services;

import com.oma.dao.AddressDAO;
import com.oma.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImplementation.class);

    @Autowired
    AddressDAO addressDAO;

    @Override
    @Transactional
    public List<Address> getAllAddresses() {
        logger.warn("Exposing all the address list from service layer");
        return addressDAO.getAllAddresses();
    }

    @Override
    @Transactional
    public void saveAddress(Address address) {
        logger.info("Save address from the service layer!");
        addressDAO.saveAddress(address);
    }

    @Override
    @Transactional
    public Address getAddressForId(long id){
        logger.warn("Get address for id from the service layer\"");
        return addressDAO.getAddressForId(id);
    }

    @Override
    @Transactional
    public void updateAddressForId(long id, Address address) {
        logger.warn("Exposing update address from the service layer!");
        addressDAO.updateAddress(id, address);
    }

    @Override
    @Transactional
    public void removeAddressWithId(long id) {
        logger.warn("Removal attempt address for id from service layer!");
        addressDAO.removeAddress(id);
    }
}
