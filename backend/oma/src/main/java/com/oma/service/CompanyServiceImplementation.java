package com.oma.service;

import com.oma.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImplementation implements CompanyService {

    @Override
    public List<Company> getAllCompany() {
        for (Company company : companyList) {
            System.out.println(company);
        }
        return companyList;
    }

    @Override
    public void addCompany(Company company) {
        this.getAllCompany().add(company);
    }

    @Override
    public void removeCompany(Company company) {
        this.getAllCompany().remove(company);
    }
}
