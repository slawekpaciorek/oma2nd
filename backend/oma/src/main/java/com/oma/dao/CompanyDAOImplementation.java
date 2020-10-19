package com.oma.dao;

import com.oma.model.Company;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
        return null;
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
