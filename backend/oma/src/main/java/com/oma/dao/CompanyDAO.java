package com.oma.dao;

import com.oma.model.Company;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CompanyDAO {

    List<Company>company = new ArrayList<>();

    void save(Company company);

    List<Company> getAll();

    Company getCompanyById(long id);

    void removeCompany(long id, Company company);

    void updateCompany(long id, Company company);
}
