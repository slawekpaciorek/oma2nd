package com.oma.services;

import com.oma.dao.UserDAO;
import com.oma.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class.getName());

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public void addUser(User user) {
         logger.warn("Save user from service layer");
         userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        logger.info("Get all the user list from service layer");
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public User findUserById(long id) {
        logger.info("Find user by id from service layer");
        return userDAO.findUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(long id,User user) {
        logger.warn("Update user by id from service layer");
        userDAO.updateUser(id,user);
    }

    @Override
    @Transactional
    public void removeUser(long id, User user) {
        logger.warn("Remove user by id from service layer");
        userDAO.removeUser(id,user);
    }

}
