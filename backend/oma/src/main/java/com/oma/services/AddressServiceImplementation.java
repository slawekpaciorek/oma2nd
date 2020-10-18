package com.oma.services;

import com.oma.dao.AddressDAO;
import com.oma.dao.AddressDAOImplementation;
import com.oma.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {

    @Autowired
    AddressDAO addressDAO;

    @Override
    @Transactional
    public List<Address> getAllAddresses() {
        return addressDAO.getAllAddresses();
    }

    @Override
    @Transactional
    public void saveAddress(Address address) {
        addressDAO.saveAddress(address);
    }

    @Override
    @Transactional
    public Address getAddressForId(long id){
        return addressDAO.getAddressForId(id);
    }

    @Override
    @Transactional
    public void updateAddressForId(long id, Address address) {
        addressDAO.updateAddress(id, address);
    }

    @Override
    @Transactional
    public void removeAddressWithId(long id) {
        addressDAO.removeAddress(id);
    }
}
