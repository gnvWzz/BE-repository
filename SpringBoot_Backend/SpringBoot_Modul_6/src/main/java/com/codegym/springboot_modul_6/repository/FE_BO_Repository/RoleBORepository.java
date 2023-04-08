package com.codegym.springboot_modul_6.repository.FE_BO_Repository;

import com.codegym.springboot_modul_6.model.FE_BO_Model.entity.RoleBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleBORepository extends CrudRepository<RoleBO,Long> {
}
