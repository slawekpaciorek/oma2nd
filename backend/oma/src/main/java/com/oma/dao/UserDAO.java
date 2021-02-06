package com.oma.dao;

import com.oma.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserDAO {

    List<User> user = new ArrayList<>();

    void saveUser(User user);

    List<User> getAll();

    User findUserById(long id);

    void updateUser(long id,User user);

    void removeUser(long id, User user);

    List<User> findUserForCompany(long companyId);
}
