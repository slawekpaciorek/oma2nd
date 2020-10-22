package com.oma.dao;

import com.oma.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class UserDAOImplementation implements UserDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveUser(User user) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.save(user);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return factoryCurrentSession.createQuery("from User").getResultList();
    }

    @Override
    @Transactional
    public User findUserById(long id) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return (User) factoryCurrentSession.createQuery("from User user where user.id=:id")
                .setParameter("id",id)
                .getSingleResult();
    }
}
