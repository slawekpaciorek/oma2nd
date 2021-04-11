package com.oma.services;

import com.oma.model.Address;
import com.oma.model.Company;
import com.oma.model.User;
import com.oma.utils.DBCleaner;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    SessionFactory sessionFactory;

    public Session session;

    @BeforeEach
    void setUp() {
        cleanDB();
        session = sessionFactory.openSession();
    }

    @Test
    public void shouldSaveUserInDB(){
        //given
        User expected = new User();
        expected.setPassword("Testy");
        expected.setName("Kamil");
        expected.setUsername("Kamil");
        expected.setMobilePhone(788788788);
        //when
        userService.addUser(expected);
        session.beginTransaction();
        User result = session.createQuery("from User user where user.username=:username and user.name=:name ", User.class)
                .setParameter("username",expected.getUsername())
                .setParameter("name",expected.getName())
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        //then
        assertEquals(result,expected);
    }

    @Test
    public void shouldGetAllUserFromDb(){
       //given
        List<User> expected = new ArrayList<>();

        //when
        for(int i = 0; i < Math.abs(new Random().nextInt(10)); i++){
            User user = new User(RandomString.make(6),RandomString.make(6),"operator", new Random().nextInt(100000000));
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            expected.add(user);
        }
        List<User> actual = userService.getAllUser();
        session.close();
        //then
        assertEquals(expected,actual);
    }

    @Test
    public void shouldUserById(){
        User expected = new User();
        expected.setUsername("user_example");
        expected.setName("Name_example");

        userService.addUser(expected);
        long userId = expected.getId();
        User result = userService.findUserById(userId);

        assertEquals(expected,result);
    }
    @Test
    public void shouldUpdateUser(){
        //given
        User input = new User();
        input.setName("Kamil");
        input.setUsername("War");
        input.setMobilePhone(543543543);
        User update = new User();
        update.setName("Update");
        //when
        userService.addUser(input);
        userService.updateUser(input.getId(), update);
        User expected = userService.findUserById(input.getId());
        //then
        assertEquals(expected.getMobilePhone(),update.getMobilePhone());

    }

    @Test
    public void removeUser(){
        User expected = new User();
        expected.setName("Kamil");
        expected.setUsername("War");
        expected.setMobilePhone(543543543);
        userService.addUser(expected);
        long id = expected.getId();
        session.beginTransaction();
        userService.removeUser(id,expected);
    }

    @Test
    public void shouldFindUserForCompany(){
//        given
        User user = new User("name", "name@lastName", "manager",500500500);
        Company company = new Company("companyName", "2015123865", new Address("streetName", "00-000","City"));
        company.addUser(user);
//        when
        companyService.saveCompany(company);
        List<User> expected = userService.getUserForCompany(company.getId());
//        then
        assertTrue(expected.contains(user));
    }

    private void cleanDB() {
        DBCleaner dbCleaner = new DBCleaner();
        dbCleaner.setSessionFactory(sessionFactory);
        dbCleaner.setTableNames(new String[]{"User", "Company"});
        dbCleaner.cleanDB();
    }
}
