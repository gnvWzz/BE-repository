package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Roles;

import java.util.Optional;

public interface RolesService extends IGeneralService<Roles>{

    Optional<Roles> findRolesByName(String name);
}
