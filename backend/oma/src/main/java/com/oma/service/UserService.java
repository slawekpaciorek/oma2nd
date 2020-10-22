package com.oma.service;

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
}
