package com.oma.dao;

import com.oma.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDAOImplementation implements AddressDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Address> getAllAddresses() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Address ", Address.class).getResultList();
    }
}
