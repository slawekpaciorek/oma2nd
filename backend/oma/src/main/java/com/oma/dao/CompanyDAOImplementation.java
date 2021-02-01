package com.oma.dao;

import com.oma.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class CompanyDAOImplementation implements CompanyDAO {

    private static final Logger logger = LoggerFactory.getLogger(CompanyDAOImplementation.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Company company) {
        logger.info("Trying to save the company to the database from the repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.save(company);
    }

    @Override
    @Transactional
    public List<Company> getAll() {
        logger.warn("Trying get all the company list from repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
       return factoryCurrentSession.createQuery("from Company").getResultList();
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
           update.setName(company.getName());
           factoryCurrentSession.update(update);
   }

    @Override
    @Transactional
    public void removeCompany(long id, Company company) {
        logger.warn("Trying remove company for id from repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.remove(company);
    }
}
