package com.oma.services;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.oma.dao.RoleDAO;
import com.oma.dao.UserDAO;
import com.oma.model.Role;
import com.oma.model.User;
import com.oma.model.UserPrivileges;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class.getName());

    @Autowired
    UserDAO userDAO;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public void addUser(User user) {
         logger.warn("Save user from service layer");
         if(user.getPassword()!=null) {
             String temp = passwordEncoder.encode(user.getPassword());
             user.setPassword(temp);
         }
         if(user.getDeliveryPoints()!= null && (user.getPrivileges().equals(UserPrivileges.operator) || user.getPrivileges().equals(UserPrivileges.manager)))
             user.setRoles(Arrays.asList((roleDAO.findRoleByName("ROLE_USER"))));
         if(user.getDeliveryPoints()!= null && (user.getPrivileges().equals(UserPrivileges.administrator)))
             user.setRoles(Arrays.asList(roleDAO.findRoleByName("ROLE_ADMIN")));
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

    @Override
    @Transactional
    public List<User> getUserForCompany(long companyId) {
        return userDAO.findUserForCompany(companyId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findUserByName(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
