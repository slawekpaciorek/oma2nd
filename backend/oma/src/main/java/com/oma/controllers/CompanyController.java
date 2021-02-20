package com.oma.controllers;

import com.oma.model.Company;
import com.oma.model.User;
import com.oma.services.CompanyService;
import com.oma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<Company> companies = companyService.getAllCompany();
        return companies;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void saveCompanyInDB(@RequestBody Company company){
        if(company.getUsers()!=null) {
            company.getUsers().forEach(x -> x.setCompany(company));
        }
        companyService.saveCompany(company);
    }

    @GetMapping(value = "/company-details", produces = "application/json")
    public Company getCompanyDetails(@RequestParam("id")String companyId){
        Company company = companyService.getCompanyById(Long.parseLong(companyId));
        return company;
    }

    @GetMapping(value = "/remove", produces = "application/json")
    @DeleteMapping("/company/{id}")
    public Company removeCompanyById(@RequestParam("id")String companyId){
        Company company = companyService.getCompanyById(Long.parseLong(companyId));
        companyService.removeCompany(company.getId(), company);
        return company;
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public Company updateCompany(@RequestBody Company company, @RequestParam("id") String companyId){
        long id = Long.parseLong(companyId);
        companyService.updateCompany(id, company);
        return company;
    }

    @GetMapping(value = "/display-users", produces = "application/json")
    public List<User> showUsersForCompany(@SessionAttribute("companyId")String companyId){
        List<User> users = companyService.getCompanyById(Long.parseLong(companyId)).getUsers();
        return users;
    }

}
