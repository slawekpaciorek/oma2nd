package com.oma.service;

import com.oma.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Autowired
    SessionFactory sessionFactory;

    private Session session;

    @BeforeEach
    void setUp() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Company").executeUpdate();
        session.getTransaction().commit();
    }

    @AfterEach
    void cleanUp() {
        session.close();
    }

    @Test
    public void shouldSaveCompanyInDb(){
        //given
        Company expected = new Company();
        expected.setName("Company_example");
        //when
        companyService.saveCompany(expected);
        Company result = (Company) session.createQuery("from Company company where company.name=:name")
//                .setParameter("address",company.getAddress().getIdAddress())
//                .setParameter("taxNumberId",company.getTaxNumberId())
                .setParameter("name",expected.getName())
                .getSingleResult();
        //then
        Assertions.assertEquals(result,expected);
    }
    @Test
    public void shouldGetAllCompanyInDb(){
        Company company = new Company();
        List<Company> companies = new ArrayList<>();
        company.setName("Company_example_1");
        company.setName("Company_example_2");
        companies.add(company);
        session.beginTransaction();

        for (Company company1 : companies) {
            session.saveOrUpdate(company1);
        }
        session.getTransaction().commit();
        List<Company> actual = companyService.getAllCompany();

        Assertions.assertEquals(companies,actual);

    }
    @Test
    public void shouldGetCompanyById(){
        Company company = new Company();
        company.setName("OMA");

        companyService.saveCompany(company);
        long companyId = company.getId();

        Company result = companyService.getCompanyById(companyId);

        Assertions.assertEquals(company,result);

    }
}


