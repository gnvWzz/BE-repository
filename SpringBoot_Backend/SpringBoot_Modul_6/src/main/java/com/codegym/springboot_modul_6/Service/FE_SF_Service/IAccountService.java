package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Account;

import java.util.Optional;

public interface IAccountService extends IGeneralService<Account>{

    Optional<Account>  findAccountByUsername(String username);
    Optional<Account>  findAccountByUEmail(String username);
}
