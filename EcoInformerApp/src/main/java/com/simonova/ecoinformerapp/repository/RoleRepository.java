package com.simonova.ecoinformerapp.repository;

import com.simonova.ecoinformerapp.models.ERole;
import com.simonova.ecoinformerapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
