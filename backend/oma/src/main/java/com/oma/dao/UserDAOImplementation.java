package com.oma.dao;

import com.oma.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
