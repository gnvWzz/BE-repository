package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Roles;

import java.util.Optional;

public interface RolesService extends IGeneralService<Roles>{

    Optional<Roles> findRolesByName(String name);
}
