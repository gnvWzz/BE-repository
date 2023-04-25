package com.codegym.springboot_modul_6.service.fe_sf_service;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Roles;

import java.util.Optional;

public interface RolesService extends GeneralService<Roles> {

    Optional<Roles> findRolesByName(String name);
}
