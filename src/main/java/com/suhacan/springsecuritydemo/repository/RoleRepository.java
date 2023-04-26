package com.suhacan.springsecuritydemo.repository;

import com.suhacan.springsecuritydemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
