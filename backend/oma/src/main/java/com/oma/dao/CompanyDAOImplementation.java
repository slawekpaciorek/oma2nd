package com.oma.dao;

import com.oma.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
    public Company getCompanyForTaxNumberId(int id) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        Query query = factoryCurrentSession.createQuery("from Company company where company.taxNumberId",
                Company.class);
        query.setParameter("taxId",id);
        return (Company) query.getSingleResult();
    }

    @Override
    public void updateCompany(long id,Company company) {
//        Session factoryCurrentSession = sessionFactory.getCurrentSession();
//        Company companyForID = getCompanyForID(id);
//        companyForID.
    }

    @Override
    public void removeCompany(long id, Company company) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.remove(company);
    }

}
