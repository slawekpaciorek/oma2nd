package com.oma.service;

import com.oma.dao.CompanyDAO;
import com.oma.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImplementation implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    @Transactional
    public void saveCompany(Company company) {
          companyDAO.save(company);
    }

    @Override
    @Transactional
    public List<Company> getAllCompany() {
        return companyDAO.getAll();
    }

    @Override
    @Transactional
    public Company getCompanyById(long id) {
        return companyDAO.getCompanyById(id);
    }

    @Override
    @Transactional
    public void updateCompany(long id,Company company) {
        companyDAO.updateCompany(id,company);
    }

    @Override
    @Transactional
    public void removeCompany(long id, Company company) {
        companyDAO.removeCompany(id,company);
    }
}
