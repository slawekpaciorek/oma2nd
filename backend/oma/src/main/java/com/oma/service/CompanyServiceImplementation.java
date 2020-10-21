package com.oma.service;

import com.oma.dao.CompanyDAO;
import com.oma.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImplementation implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    @Transactional
    public void saveCompany(Company company) {
          companyDAO.save(company);
    }
}
