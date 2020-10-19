package com.oma.dao;

import com.oma.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CompanyDAOImplementation implements CompanyDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Company>getAllCompany(){
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return factoryCurrentSession.createQuery("from Company",Company.class).getResultList();
    }

    @Override
    public void saveCompany(Company company) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.save(company);
    }

    @Override
    public Company getCompanyForID(long id) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        Query query = factoryCurrentSession.createQuery("from Company company where company.id",
                Company.class);
        query.setParameter("id",id);
        return (Company) query.getSingleResult();
    }


    @Override
    public void updateCompany(Company company) {
        //TO DO
    }

    @Override
    public void removeCompany(long id) {
        //TO DO
    }
}
