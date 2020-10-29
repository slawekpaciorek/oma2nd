package com.oma.services;

import com.oma.dao.UserDAO;
import com.oma.model.User;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserDAO userDAO;

    private final Logger log = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Override
    @Transactional
    public void addUser(User user) {
        log.trace("[add User] User added.");
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        log.trace("[get all] Get all users.");
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public User findUserById(long id) {
        log.trace("[find by ID] Find user by ID.");
        return userDAO.findUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(long id,User user) {
        log.trace("[update] User updated.");
        userDAO.updateUser(id,user);
    }

    @Override
    @Transactional
    public void removeUser(long id, User user) {
        log.trace("[remove] User removed.");
        userDAO.removeUser(id,user);
    }

}
