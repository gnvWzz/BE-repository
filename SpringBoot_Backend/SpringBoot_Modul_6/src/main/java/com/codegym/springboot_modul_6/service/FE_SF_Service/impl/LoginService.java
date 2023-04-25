package com.codegym.springboot_modul_6.service.fe_sf_service.impl;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Account;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.ILoginRepository;
import com.codegym.springboot_modul_6.service.fe_sf_service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements GeneralService<Account> {

    @Autowired
    ILoginRepository loginRepository;
    @Override
    public Iterable<Account> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return loginRepository.findById(id);
    }

    @Override
    public void save(Account account) {
        loginRepository.save(account);
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }
}
