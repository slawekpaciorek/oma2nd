package com.oma.services;

import com.oma.model.Company;
import com.oma.utils.DBCleaner;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        DBCleaner dbCleaner = new DBCleaner();
        dbCleaner.setSessionFactory(sessionFactory);
        dbCleaner.setTableNames(new String[]{"Company", "Address"});
        dbCleaner.cleanDB();
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
        assertEquals(result,expected);
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

        assertEquals(companies,actual);

    }
    @Test
    public void shouldFindCompanyById(){
        Company company = new Company();
        company.setName("OMA");

        companyService.saveCompany(company);
        long companyId = company.getId();

        Company result = companyService.getCompanyById(companyId);

        assertEquals(company,result);

    }
    @Test
    public void shouldUpdateCompany(){
    //given
        Company firstVersion = new Company();
        firstVersion.setName("AXA");

        Company update = new Company();
        update.setName("Update");
        //when
        companyService.saveCompany(firstVersion);
        companyService.updateCompany(firstVersion.getId(),update);
        Company result = companyService.getCompanyById(firstVersion.getId());
        //then
        assertEquals(update, result);
    }
    @Test
    public void shouldRemoveCompany(){
        Company expected = new Company();
        expected.setName("Example_1"); 
        companyService.saveCompany(expected);
        long id = expected.getId();
        session.beginTransaction();
        companyService.removeCompany(id,expected);
    }
}


