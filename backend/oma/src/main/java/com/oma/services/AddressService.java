package com.oma.services;

import com.oma.model.Address;
import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();

    void saveAddress(Address address);

    Address getAddressForId(long id);

    void updateAddressForId(long id, Address update);
}
