package com.codegym.springboot_modul_6.repository.fe_sf_repository;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findByName(String name);
}
