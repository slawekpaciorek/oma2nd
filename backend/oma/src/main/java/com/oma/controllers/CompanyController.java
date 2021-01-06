package com.oma.controllers;

import com.oma.model.Company;
import com.oma.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("company")
@CrossOrigin({"http://localhost:4200", "http://localhost:5555"})
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping(value = "/all-companies", produces = "application/json")
    public List<Company> getAllCompanies(){
//        return companyService.getDefaultCompanies();
        return companyService.getAllWithAddresses();
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void saveCompanyInDB(@RequestBody Company company){
        companyService.saveCompany(company);
    }

}
