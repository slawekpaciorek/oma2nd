package com.oma.services;

import com.oma.model.Address;
import com.oma.model.Company;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public interface CompanyService {

    List<Company>companies = new ArrayList<>();

    Company saveCompany(Company company);

    List<Company> getAllCompany();

    List<Company> getAllWithAddresses();

    Company getCompanyById(long id);

    void updateCompany(long id,Company company);

    void removeCompany(long id, Company company);

    default List<Company> getDefaultCompanies(){

        List<Company>companies = new ArrayList<>();
        companies.add(new Company("Trading Inc", 900345670, new Address("9th 243, b2", "00-400", "New York")));
        companies.add(new Company("Cleaning&Trading Co", 900345681, new Address("ZingHan", "d3-090", "Hong-Kong")));
        companies.add(new Company("It Support", 900345692, new Address("Marszalkowska st 297", "01-001", "Warsaw")));

        return companies;
    }
}
