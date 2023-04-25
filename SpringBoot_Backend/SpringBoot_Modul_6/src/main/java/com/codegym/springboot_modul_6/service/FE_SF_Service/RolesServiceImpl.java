package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Roles;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService{
    @Autowired
    IRolesRepository iRolesRepository;

    @Override
    public Iterable<Roles> findAll() {
        return iRolesRepository.findAll();
    }

    @Override
    public Optional<Roles> findById(Long id) {
        return iRolesRepository.findById(id);
    }

    @Override
    public void save(Roles roles) {
        iRolesRepository.save(roles);
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public Optional<Roles> findRolesByName(String name) {
        return iRolesRepository.findByName(name);
    }
}
