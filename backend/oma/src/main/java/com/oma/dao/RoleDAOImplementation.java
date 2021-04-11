package com.oma.dao;

import com.oma.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImplementation implements RoleDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Role findRoleByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Role role where role.name=:name", Role.class)
                .setParameter("name", roleName)
                .getSingleResult();
    }
}
