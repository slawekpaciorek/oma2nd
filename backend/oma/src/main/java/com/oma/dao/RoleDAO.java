package com.oma.dao;

import com.oma.model.Role;

public interface RoleDAO {

    Role findRoleByName(String role_employee);
}
