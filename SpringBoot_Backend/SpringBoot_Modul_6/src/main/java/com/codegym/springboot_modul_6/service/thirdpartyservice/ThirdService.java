package com.codegym.springboot_modul_6.service.thirdpartyservice;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.AccountRoles;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Roles;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.AccountDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.security.JwtService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.IAccountService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.IProductService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.RolesService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.LongMapper;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThirdService {

    @Autowired
    private IProductService productService;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private LongMapper mapper;

    @Autowired
    private RolesService rolesService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private JwtService jwtService;


    public Page<ProductSFDto> pageProductSFDto(Page<ProductSF> pageEntity){
        List<ProductSFDto> productSFList = mapper.mapperProductSFDto(pageEntity.getContent());
        Page<ProductSFDto> page = new PageImpl<ProductSFDto>(productSFList, pageEntity.getPageable(), pageEntity.getTotalElements());
        return page;
    }

    public Account signUp(AccountDto accountDto){
        Account account = new Account();
        AccountRoles accountRoles = new AccountRoles();
        List<AccountRoles> accountRolesList = new ArrayList<>();

        Roles roles = rolesService.findRolesByName("ROLE_USER").get();
        account = requestMapper.toAccount(accountDto);
        accountRoles.setAccount(account);
        accountRoles.setRoles(roles);
        accountRolesList.add(accountRoles);
        account.setRolesList(accountRolesList);
        iAccountService.save(account);
        return account;
    }

    public String login(AccountDto accountDto){
        boolean isLogin = iAccountService.checkLogin(accountDto.getUsername(), accountDto.getPassword());
        if (isLogin){
            Account account = iAccountService.findAccountByUsername(accountDto.getUsername()).get();
            String jwt = jwtService.generateTokenLogin(account);
          return jwt;
        }
        return null;
    }

    public Account checkValidateEmail(String email){
        Account account = iAccountService.findAccountByUEmail(email).get();
        return account;
    }

    public Account checkValidateUsernmae(String username){
        Account account = iAccountService.findAccountByUsername(username).get();
        return account;
    }


}
