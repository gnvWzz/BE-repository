
package com.codegym.springboot_modul_6.service.fe_sf_service;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Account;

import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;

public interface AccountService extends GeneralService<Account>, UserDetailsService {
//public interface IAccountService extends IGeneralService<Account> {

    Optional<Account>  findAccountByUsername(String username);
    Optional<Account>  findAccountByUEmail(String username);

    Optional<Account>  findAccountByPhone(String username);

    boolean checkLogin(String username, String password);
}
