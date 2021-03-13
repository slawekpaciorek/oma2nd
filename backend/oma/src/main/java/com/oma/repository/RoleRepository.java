package com.oma.repository;

import com.oma.model.ERole;
import com.oma.model.Role;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByNam(ERole name);
}
