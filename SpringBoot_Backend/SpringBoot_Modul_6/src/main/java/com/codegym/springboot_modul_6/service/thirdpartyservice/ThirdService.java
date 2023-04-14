package com.codegym.springboot_modul_6.service.thirdpartyservice;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.*;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.AccountDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.security.JwtProvider;
import com.codegym.springboot_modul_6.service.FE_SF_Service.IAccountService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ICategoryService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.RolesService;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductRepositorySF;

import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.LongMapper;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ThirdService {

    @Autowired
    private IProductRepositorySF productRepositorySF;

    private static final CategoryCache categoryCache = CategoryCache.getCategoryCache();

    @Autowired
    private LongMapper mapper;

    @Autowired
    private RolesService rolesService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private RequestMapper requestMapper;


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
        accountService.save(account);
        return account;
    }

    public Account signUpOwner(AccountDto accountDto){
        Account account = new Account();
        AccountRoles accountRoles = new AccountRoles();
        List<AccountRoles> accountRolesList = new ArrayList<>();

        Roles roles = rolesService.findRolesByName("ROLE_OWNER").get();
        account = requestMapper.toAccount(accountDto);
        accountRoles.setAccount(account);
        accountRoles.setRoles(roles);
        accountRolesList.add(accountRoles);
        account.setRolesList(accountRolesList);
        accountService.save(account);
        return account;
    }

    public String login(AccountDto accountDto){
        boolean isLogin = accountService.checkLogin(accountDto.getUsername(), accountDto.getPassword());
        Account account = accountService.findAccountByUsername(accountDto.getUsername()).get();
        String role = account.getRolesList().get(0).getRoles().getName();
        if (isLogin && (role.equals("ROLE_USER"))){
            String jwt = jwtProvider.generateTokenLogin(account);
          return jwt;
        }
        return null;
    }

    public String loginOwner(AccountDto accountDto){
        boolean isLogin = accountService.checkLogin(accountDto.getUsername(), accountDto.getPassword());
        Account account = accountService.findAccountByUsername(accountDto.getUsername()).get();
        String role = account.getRolesList().get(0).getRoles().getName();
        if (isLogin && (role.equals("ROLE_OWNER"))){

            String jwt = jwtProvider.generateTokenLogin(account);
            return jwt;
        }
        return null;
    }

    public Account checkValidateEmail(String email){
        Account account = accountService.findAccountByUEmail(email).get();
        return account;
    }

    public Account checkValidateUsernmae(String username){
        Account account = accountService.findAccountByUsername(username).get();
        return account;
    }

    public Account checkValidatePhone(String phone){
        Account account = accountService.findAccountByPhone(phone).get();
        return account;
    }

    public Page<ProductSFDto> productSFDtoPage(Page<ProductSF> entity){
        List<ProductSFDto> productSFDtos = mapper.mapperProductSFDto(entity.getContent());
        Page<ProductSFDto> page = new PageImpl<>(productSFDtos, entity.getPageable(), entity.getTotalElements());
        return page;
    }




}
