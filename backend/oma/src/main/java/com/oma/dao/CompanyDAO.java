package com.oma.dao;

import com.oma.model.Company;

import java.util.List;

public interface CompanyDAO {

    List<Company> getAllCompany();

    void saveCompany(Company company);
    Company getCompanyForID(long id);
    void updateCompany(Company company);
    void removeCompany(long id);

}
