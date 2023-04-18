package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements IGeneralService<Account> {

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
