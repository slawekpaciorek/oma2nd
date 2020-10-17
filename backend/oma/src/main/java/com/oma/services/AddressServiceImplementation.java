package com.oma.services;

import com.oma.dao.AddressDAO;
import com.oma.dao.AddressDAOImplementation;
import com.oma.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
