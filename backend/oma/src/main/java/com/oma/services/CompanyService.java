package com.oma.services;

import com.oma.model.Company;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public interface CompanyService {

    List<Company>companies = new ArrayList<>();

    void saveCompany(Company company);

    List<Company> getAllCompany();

    Company getCompanyById(long id);

    void updateCompany(long id,Company company);

    void removeCompany(long id, Company company);

    default List<Company> getDefaultCompanies(){

        List<Company>companies = new ArrayList<>();
        companies.add(new Company("example", 900345670));
        companies.add(new Company("example1", 900345681));
        companies.add(new Company("example2", 900345692));

        return companies;
    }
}
