package com.oma.dao;

import com.oma.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompanyDAOImplementation implements CompanyDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Company company) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.save(company);
    }
}
