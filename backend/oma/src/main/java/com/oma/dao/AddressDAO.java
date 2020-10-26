package com.oma.dao;

import com.oma.model.Address;
import java.util.List;

public interface AddressDAO {

    List<Address> getAllAddresses();

    void saveAddress(Address address);

    Address getAddressForId(long id);

    void updateAddress(long id, Address address);

    void removeAddress(long id);
}
