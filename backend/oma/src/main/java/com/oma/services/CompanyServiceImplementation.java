package com.oma.services;

import com.oma.dao.CompanyDAO;
import com.oma.model.Company;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImplementation implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    private final Logger log = LoggerFactory.getLogger(CompanyServiceImplementation.class);

    @Override
    @Transactional
    public void saveCompany(Company company) {
        log.trace("[save] Company saved.");
          companyDAO.save(company);
    }

    @Override
    @Transactional
    public List<Company> getAllCompany() {
        log.trace("[get all] Get all Company.");
        return companyDAO.getAll();
    }

    @Override
    @Transactional
    public Company getCompanyById(long id) {
        log.trace("[get by ID] Get Company by ID.");
        return companyDAO.getCompanyById(id);
    }

    @Override
    @Transactional
    public void updateCompany(long id,Company company) {
        log.trace("[update] Company updated.");
        companyDAO.updateCompany(id,company);
    }

    @Override
    @Transactional
    public void removeCompany(long id, Company company) {
        log.trace("[remove] Company removed.");
        companyDAO.removeCompany(id,company);
    }
}
