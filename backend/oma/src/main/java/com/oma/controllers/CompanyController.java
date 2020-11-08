package com.oma.controllers;

import com.oma.model.Company;
import com.oma.services.CompanyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping(value = "/all-companies", produces = "application/json")
    public List<Company> getAllCompanies(){
        return companyService.getAllCompany();
    }
}
