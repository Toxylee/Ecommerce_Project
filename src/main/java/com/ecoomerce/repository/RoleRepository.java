package com.ecoomerce.repository;


import com.ecoomerce.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByAuthority(String authority);

    Optional<Roles> findRolesByRoleId(Integer roleId);
}