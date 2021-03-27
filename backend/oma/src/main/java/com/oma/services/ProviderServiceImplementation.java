package com.oma.services;

import com.oma.dao.AddressDAO;
import com.oma.dao.ProviderDAO;
import com.oma.dao.UserDAO;
import com.oma.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProviderServiceImplementation implements ProviderService{

    @Autowired
    ProviderDAO providerDAO;

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    UserDAO userDAO;

    @Transactional
    @Override
    public void saveProvider(Provider provider) {
        if(provider.getAddress()!=null){
            addressDAO.saveAddress(provider.getAddress());
        }
        if(provider.getAdmins()!=null)
            provider.getAdmins().forEach(user -> userDAO.saveUser(user));
        providerDAO.saveProvider(provider);
    }

    @Transactional
    @Override
    public void updateProvider(Provider provider) {
        providerDAO.updateProvider(provider);
    }
}
