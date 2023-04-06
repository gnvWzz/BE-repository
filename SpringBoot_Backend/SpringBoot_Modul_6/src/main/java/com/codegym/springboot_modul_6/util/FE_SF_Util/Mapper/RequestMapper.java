package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Categories;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.AccountDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.CategoriesDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestMapper {


    public Account toAccount(AccountDto accountDto){
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        return account;
    }


}
