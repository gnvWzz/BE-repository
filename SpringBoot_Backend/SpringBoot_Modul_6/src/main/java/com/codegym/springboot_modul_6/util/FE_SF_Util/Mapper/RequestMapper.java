package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.AccountDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    public Account toAccount(AccountDto accountDto){
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        return account;
    }

}
