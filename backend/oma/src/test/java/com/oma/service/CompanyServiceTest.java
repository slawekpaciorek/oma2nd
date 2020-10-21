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
}


