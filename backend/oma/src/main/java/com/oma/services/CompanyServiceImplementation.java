package com.oma.services;

import com.oma.dao.AddressDAO;
import com.oma.dao.CompanyDAO;
import com.oma.dao.UserDAO;
import com.oma.dao.UserDAOImplementation;
import com.oma.model.Address;
import com.oma.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImplementation implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImplementation.class);

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public Company saveCompany(Company company) {
        if(company.getAddress()!=null)
            addressDAO.saveAddress(company.getAddress());
        companyDAO.save(company);
        if(company.getUsers()!=null){
            company.getUsers().forEach(x->x.setCompany(company));
            company.getUsers().forEach(x->userDAO.saveUser(x));
        }
        return company;

    }

    @Override
    @Transactional
    public List<Company> getAllCompany() {
        logger.info("Get all the company list from service layer");
        return companyDAO.getAll();
    }

    @Override
    @Transactional
    public Company getCompanyById(long id) {
        logger.info("Get company for id from the service layer");
        return companyDAO.getCompanyById(id);
    }

    @Override
    @Transactional
    public void updateCompany(long id,Company company){
        Company temp = getCompanyById(id);
        if(temp.getAddress()!=null && !company.getAddress().equals(temp.getAddress())){
            long addressId = temp.getAddress().getId();
            addressDAO.updateAddress(addressId, company.getAddress());
        }
    }

    @Override
    @Transactional
    public void removeCompany(long id, Company company) {
        logger.warn("Remove company for id from service layer!");
        companyDAO.removeCompany(id,company);
    }

    @Override
    @Transactional
    public List<Company> getAllWithAddresses() {
        return companyDAO.getAllWithAddresses();
    }

}
