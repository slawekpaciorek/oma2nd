package com.oma.dao;

import com.oma.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserDAO {

    List<User> user = new ArrayList<>();

    void saveUser(User user);
}
