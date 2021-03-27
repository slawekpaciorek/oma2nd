package com.oma.dao;

import com.oma.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class UserDAOImplementation implements UserDAO{

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImplementation.class.getName());

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveUser(User user){
        logger.info("Trying to save the user to the database from the repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.saveOrUpdate(user);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        logger.warn("Trying get all the user list from repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return factoryCurrentSession.createQuery("from User").getResultList();
    }

    @Override
    @Transactional
    public User findUserById(long id) {
        logger.info("Trying find user for id from the repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return (User) factoryCurrentSession.createQuery("from User user where user.id=:id")
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void updateUser(long id,User user) {
        logger.warn("Trying update user from the repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        User upd = findUserById(id);
        upd.setName(user.getName());
        upd.setUsername(user.getUsername());
        upd.setMobilePhone(user.getMobilePhone());
        factoryCurrentSession.update(upd);
    }

    @Override
    @Transactional
    public void removeUser(long id, User user) {
        logger.warn("Trying remove user from the repositories layer!");
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        factoryCurrentSession.remove(user);
    }

    @Override
    @Transactional
    public List<User> findUserForCompany(long companyId) {
        Session factoryCurrentSession = sessionFactory.getCurrentSession();
        return factoryCurrentSession.createQuery("from User user where user.company.id=:companyId")
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Override
    @Transactional
    public List<User> getUsersForCompany(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User u where u.company.id = :companyId")
                .setParameter("companyId", id).getResultList();
    }
}
