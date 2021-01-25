package com.oma.dao;

import com.oma.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AddressDAOImplementation implements AddressDAO {

    private static final Logger logger = LoggerFactory.getLogger(AddressDAOImplementation.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Address> getAllAddresses() {
        logger.warn("Exposing all the address list from repositories layer");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Address ", Address.class).getResultList();
    }

    @Override
    public void saveAddress(Address address) {
        logger.info("Trying to save the address to the database from the repositories layer!");
        Session session = sessionFactory.getCurrentSession();
        session.save(address);
    }

    @Override
    public Address getAddressForId(long id){
        logger.info("Trying get address for id from the repositories layer");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Address address where address.id = :id", Address.class);
        query.setParameter("id", id);
        return (Address) query.getSingleResult();
    }

    @Override
    public void updateAddress(long id, Address address) {
        logger.warn("Trying update address from the repositories layer!");
        Session session = sessionFactory.getCurrentSession();
        Address temp = getAddressForId(id);
        temp.setCity(address.getCity());
        temp.setStreetNameAndNumber(address.getStreetNameAndNumber());
        temp.setMobilePhoneNumber(address.getMobilePhoneNumber());
        temp.setZipCode(address.getZipCode());
        session.update(temp);
    }

    @Override
    public void removeAddress(long id) {
        logger.warn("Trying remove address for id from repositories layer!");
        Session session = sessionFactory.getCurrentSession();
        Address address = getAddressForId(id);
        session.remove(address);
    }
    
}
