package com.oma.services;

import com.oma.dao.UserDAO;
import com.oma.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public User findUserById(long id) {
        return userDAO.findUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(long id,User user) {
        userDAO.updateUser(id,user);
    }

    @Override
    @Transactional
    public void removeUser(long id, User user) {
        userDAO.removeUser(id,user);
    }

    @Override
    public List<User> getUserForCompany(long companyId) {
        return userDAO.findUserForCompany(companyId);
    }
}
