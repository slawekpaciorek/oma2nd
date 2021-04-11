package com.oma.dao;

import com.oma.model.Provider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProviderDAOImplementation implements ProviderDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public void saveProvider(Provider provider) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(provider);
    }

    @Override
    public void updateProvider(Provider provider) {
        saveProvider(provider);
    }
}
