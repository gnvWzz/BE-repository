package com.codegym.springboot_modul_6.repository.FE_SF_Repository;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolesRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findByName(String name);
}
