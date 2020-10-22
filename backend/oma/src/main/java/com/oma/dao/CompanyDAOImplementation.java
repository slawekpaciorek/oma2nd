package com.oma.dao;

import com.oma.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional
    public List<Company> getAll() {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
       return factoryCurrentSession.createQuery("from Company").getResultList();
    }

    @Override
    @Transactional
    public Company getCompanyById(long id) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return (Company) factoryCurrentSession.createQuery("from Company company where company.id=:id")
                .setParameter("id",id)
                .getSingleResult();
    }
}
