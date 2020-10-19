package com.oma.service;

import com.oma.dao.CompanyDAO;
import com.oma.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public interface CompanyService {

    List<Company>companyList = new CopyOnWriteArrayList<>();

    //get All
    List<Company> getAllCompany();
    //add Company
    public void addCompany(Company company);
    //remove Company
    public void removeCompany(Company company);
}
