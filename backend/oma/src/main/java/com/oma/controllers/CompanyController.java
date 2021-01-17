package com.oma.controllers;

import com.oma.model.Company;
import com.oma.model.User;
import com.oma.services.CompanyService;
import com.oma.services.UserService;
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

    @Autowired
    UserService userService;

    @GetMapping(value = "/all-companies", produces = "application/json")
    public List<Company> getAllCompanies(){
//        return companyService.getDefaultCompanies();
        List<Company> companies = companyService.getAllCompany();
        return companies;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void saveCompanyInDB(@RequestBody Company company){
        company.getUsers().forEach(x->x.setCompany(company));
        companyService.saveCompany(company);
    }

    @GetMapping(value = "/company-details", produces = "application/json")
    public Company getCompanyDetails(@RequestParam("id")String companyId){
        Company company = companyService.getCompanyById(Long.parseLong(companyId));
        List<User> users = userService.getUserForCompany(Long.parseLong(companyId));
        company.setUsers(users);
        return company;
    }

}
