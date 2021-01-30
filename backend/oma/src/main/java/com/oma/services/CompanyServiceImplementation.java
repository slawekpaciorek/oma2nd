package com.oma.services;

import com.oma.dao.CompanyDAO;
import com.oma.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CompanyServiceImplementation implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImplementation.class);

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    @Transactional
    public void saveCompany(Company company) {
        logger.info("Save company from the service layer!");
        companyDAO.save(company);
    }

    @Override
    @Transactional
    public List<Company> getAllCompany() {
        logger.info("Get all the company list from service layer");
        return companyDAO.getAll();
    }

    @Override
    @Transactional
    public Company getCompanyById(long id) {
        logger.info("Get company for id from the service layer");
        return companyDAO.getCompanyById(id);
    }

    @Override
    @Transactional
    public void updateCompany(long id,Company company) {
        logger.warn("Update company from the service layer!");
        companyDAO.updateCompany(id,company);
    }

    @Override
    @Transactional
    public void removeCompany(long id, Company company) {
        logger.warn("Remove company for id from service layer!");
        companyDAO.removeCompany(id,company);
    }
}
