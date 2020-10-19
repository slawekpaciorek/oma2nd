package com.oma.dao;

import com.oma.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDAO {

    List<Company> getAllCompany();

    void saveCompany(Company company);
    Company getCompanyForID(long id);
    Company getCompanyForTaxNumberId(int id);
    void updateCompany(long id,Company company);
    void removeCompany(long id,Company company);

}
