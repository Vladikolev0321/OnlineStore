package com.example.onlineStore.repositories;


import com.example.onlineStore.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Set<Role> findByNameIn(Set<String> names);
}
