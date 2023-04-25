
package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Account;

import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;

public interface IAccountService extends IGeneralService<Account>, UserDetailsService {
//public interface IAccountService extends IGeneralService<Account> {

    Optional<Account>  findAccountByUsername(String username);
    Optional<Account>  findAccountByUEmail(String username);

    Optional<Account>  findAccountByPhone(String username);

    boolean checkLogin(String username, String password);
}
