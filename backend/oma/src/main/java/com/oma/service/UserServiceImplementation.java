package com.oma.service;

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

}
