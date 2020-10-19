package com.oma.dao;

import com.oma.model.Company;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyDAO {

    List<Company> getAllCompany();

    void addCompany(Company company);
    Company getCompanyForID(long id);
    Company getCompanyFromName(String name);
    Company getCompanyForTaxNumberId(int id);
    Company updateCompany(long id,Company company);
    void removeCompany(long id,Company company);
}
