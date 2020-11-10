package com.oma.dao;

import com.oma.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public class CompanyDAOImplementation implements CompanyDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Company company) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.saveOrUpdate(company);
    }

    @Override
    @Transactional
    public List<Company> getAll() {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
       return factoryCurrentSession.createQuery("from Company", Company.class).getResultList();
    }

    @Override
    @Transactional
    public Company getCompanyById(long id) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return (Company) factoryCurrentSession.createQuery("from Company company where company.id=:id")
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void updateCompany(long id,Company company) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        Company update = getCompanyById(id);
           update.setName(company.getName());
           factoryCurrentSession.update(update);
   }

    @Override
    @Transactional
    public void removeCompany(long id, Company company) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.remove(company);
    }

    @Override
    public List<Company> getAllWithAddresses() {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return factoryCurrentSession.createQuery("from Company company join fetch company.address", Company.class).getResultList();
    }
}
