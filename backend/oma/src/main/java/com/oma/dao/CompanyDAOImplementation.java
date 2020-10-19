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
    public List<Company> getAllCompany() {
        return null;
    }

    @Override
    public void addCompany(Company company) {

    }

    @Override
    public Company getCompanyForID(long id) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        Query query = factoryCurrentSession.createQuery("from Company company where company",
                Company.class);
        query.setParameter("id",id);
        return (Company) query.getSingleResult();
    }

    @Override
    public Company getCompanyFromName(String name) {
        return null;
    }

    @Override
    public Company getCompanyForTaxNumberId(int id) {
        return null;
    }

    @Override
    public Company updateCompany(long id, Company company) {
        return null;
    }

    @Override
    public void removeCompany(long id, Company company) {

    }
}
