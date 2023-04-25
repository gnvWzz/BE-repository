package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Roles;

import java.util.Optional;

public interface RolesService extends IGeneralService<Roles>{

    Optional<Roles> findRolesByName(String name);
}
