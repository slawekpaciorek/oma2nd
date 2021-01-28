package com.oma.services;

import com.oma.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public interface UserService {

    List<User> users = new ArrayList<>();

    void addUser(User user);

    List<User> getAllUser();

    User findUserById(long id);

    void updateUser(long id,User user);

    void removeUser(long id, User user);
}
