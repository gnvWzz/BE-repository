package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.Repository.FE_SF_Repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService{
    @Autowired
    IAccountRepository iAccountRepository;
    @Override
    public Iterable<Account> findAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return iAccountRepository.findById(id);
    }

    @Override
    public void save(Account account) {
    iAccountRepository.save(account);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Optional<Account> findAccountByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }

    @Override
    public Optional<Account> findAccountByUEmail(String username) {
        return iAccountRepository.findByEmail(username);
    }
}
