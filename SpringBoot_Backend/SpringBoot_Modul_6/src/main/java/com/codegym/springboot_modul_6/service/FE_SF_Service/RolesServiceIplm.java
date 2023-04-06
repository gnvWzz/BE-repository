package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Roles;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesServiceIplm implements RolesService{
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
    public void remove(Long id) {

    }

    @Override
    public Optional<Roles> findRolesByName(String name) {
        return iRolesRepository.findByName(name);
    }
}
