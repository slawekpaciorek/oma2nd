package com.oma.dao;

import com.oma.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public class CompanyDAOImplementation implements CompanyDAO {

    private static final Logger logger = LoggerFactory.getLogger(CompanyDAOImplementation.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Company company) {
        logger.info("Trying to save the company to the database from the repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.saveOrUpdate(company);
    }

    @Override
    @Transactional
    public List<Company> getAll() {
        logger.warn("Trying get all the company list from repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return factoryCurrentSession.createQuery("from Company", Company.class).getResultList();
    }

    @Override
    @Transactional
    public Company getCompanyById(long id) {
        logger.info("Trying get company for id from the repositories layer");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return (Company) factoryCurrentSession.createQuery("from Company company where company.id=:id")
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void updateCompany(long id,Company company) {
        logger.warn("Trying update company for id from the repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        Company update = getCompanyById(id);
           if(!update.equals(company)){
               if(!company.getName().equals(update.getName())){
                   update.setName(company.getName());
               }
               if(company.getTaxNumberId()!=null && !company.getTaxNumberId().equals(update.getTaxNumberId())){
                   update.setTaxNumberId(company.getTaxNumberId());
               }
           }
           factoryCurrentSession.update(update);
   }

    @Override
    @Transactional
    public void removeCompany(long id, Company company) {
        logger.warn("Trying remove company for id from repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.remove(company);
    }

    @Override
    public List<Company> getAllWithAddresses() {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return factoryCurrentSession.createQuery("from Company company join fetch company.address", Company.class).getResultList();
    }
}
