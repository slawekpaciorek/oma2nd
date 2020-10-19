package com.oma.service;

import com.oma.dao.CompanyDAO;
import com.oma.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImplementation implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;
    private long id;
    private Company company;

    @Override
    public List<Company> getAllCompany() {
        return companyDAO.getAllCompany();
    }

    @Override
    public Company getCompanyFromID(long id) {
        return companyDAO.getCompanyForID(id);
    }

    @Override
    public Company getCompanyFromName(String name) {
        return companyDAO.getCompanyFromName(name);
    }
    @Override
    public Company getCompanyForTaxNumberId(int id){
        return companyDAO.getCompanyForTaxNumberId(id);
    }
    @Override
    public void addCompany(Company company) {
        companyDAO.addCompany(company);
    }

    @Override
    public Company update(long id, Company company) {
        this.id = id;
        this.company = company;
        return companyDAO.updateCompany(id, company);
    }

    @Override
    public void removeCompany(Company company) {
        companyDAO.removeCompany(id,company);
    }
}
