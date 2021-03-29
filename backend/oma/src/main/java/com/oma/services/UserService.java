package com.oma.services;

import com.oma.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    List<User> users = new ArrayList<>();

    void addUser(User user);

    List<User> getAllUser();

    User findUserById(long id);

    void updateUser(long id,User user);

    void removeUser(long id, User user);

    List<User> getUserForCompany(long parseLong);


}
