package com.oma.service;

import com.oma.model.Company;
import com.oma.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    SessionFactory sessionFactory;

    public Session session;

    Company company;

    @BeforeEach
    void setUp() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
    }

    @AfterEach
    void cleanUp() {
        session.close();
    }

    @Test
    public void shouldSaveUserInDB(){
        //given
        User expected = new User("Kamil","Example",711788788,company);
        expected.setPassword("Testy");
        //when
        userService.addUser(expected);
        User result = (User)session.createQuery("from User user where user.username=:username and user.name=:name " +
                "and user.mobilePhone=:mobilPhone")
                .setParameter("username",expected.getUsername())
                .setParameter("name",expected.getName())
                .setParameter("mobilPhone",expected.getMobilePhone())
//                .setParameter("company",expected.getCompany())
                .getSingleResult();
        //then
        Assertions.assertEquals(result,expected);
    }
}
