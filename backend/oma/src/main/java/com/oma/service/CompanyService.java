package com.oma.service;


import com.oma.model.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public interface CompanyService {

    List<Company>companyList = new ArrayList<>();

    //get All company
    List<Company> getAllCompany();

    Company getCompanyFromID(long id);
    //add Company
    Company getCompanyFromName(String name);
    Company getCompanyForTaxNumberId(int id);
    void addCompany(Company company);
    //update Company
    Company update(long id, Company company);
    //remove Company
    void removeCompany(Company company);
}
