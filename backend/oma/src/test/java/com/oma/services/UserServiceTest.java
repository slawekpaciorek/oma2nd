package com.oma.services;

import com.oma.model.User;
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
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    SessionFactory sessionFactory;

    public Session session;

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
        User expected = new User();
        expected.setPassword("Testy");
        expected.setName("Kamil");
        expected.setUsername("Kamil");
        expected.setMobilePhone(788788788);
        //when
        userService.addUser(expected);
        User result = (User)session.createQuery("from User user where user.username=:username and user.name=:name " +
                "and user.mobilePhone=:mobilPhone")
                .setParameter("username",expected.getUsername())
                .setParameter("name",expected.getName())
                .setParameter("mobilPhone",expected.getMobilePhone())
                .getSingleResult();
        //then
        Assertions.assertEquals(result,expected);
    }

    @Test
    public void shouldGetAllUserFromDb(){
       //given
        User user = new User();
        List<User> expected = new ArrayList<>();
        user.setUsername("Wołek");
        user.setUsername("QWERTY");
        //when
        session.beginTransaction();
        for (User users : expected) {
            session.saveOrUpdate(users);
        }
        session.getTransaction().commit();
        List<User> actual = userService.getAllUser();
        //then
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void shouldUserById(){
        User expected = new User();
        expected.setUsername("user_example");
        expected.setName("Name_example");

        userService.addUser(expected);
        long userId = expected.getId();
        User result = userService.findUserById(userId);

        Assertions.assertEquals(expected,result);
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
        Assertions.assertEquals(expected.getMobilePhone(),update.getMobilePhone());

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
}
