package com.oma.service;

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
}
