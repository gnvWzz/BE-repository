package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

//import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
//import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.AccountRoles;
//import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Roles;
//import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.AccountDto;
//import com.codegym.springboot_modul_6.security.JwtService;
//import com.codegym.springboot_modul_6.service.FE_SF_Service.RolesService;
//import com.codegym.springboot_modul_6.service.FE_SF_Service.IAccountService;
//import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping(value = "/api/account")
//public class AccountController {
//    @Autowired
//    private IAccountService iAccountService;
//    @Autowired
//    private RequestMapper requestMapper;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private RolesService rolesService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> addAccount(@RequestBody AccountDto accountDto){
//        Account account = new Account();
//        AccountRoles accountRoles = new AccountRoles();
//        List<AccountRoles> accountRolesList = new ArrayList<>();
//        Roles roles = rolesService.findRolesByName("ROLE_USER").get();
//        account = requestMapper.toAccount(accountDto);
//        accountRoles.setAccount(account);
//        accountRoles.setRoles(roles);
//        accountRolesList.add(accountRoles);
//        account.setRolesList(accountRolesList);
//        iAccountService.save(account);
//        return new ResponseEntity<>("Add successfully", HttpStatus.OK);
//    }
//
//    @PostMapping("/login")
//    public  ResponseEntity<?> login(@RequestBody AccountDto accountDto){
//        boolean isLogin = iAccountService.checkLogin(accountDto.getUsername(), accountDto.getPassword());
//        if (isLogin){
//            Account account = iAccountService.findAccountByUsername(accountDto.getUsername()).get();
//            String jwt = jwtService.generateTokenLogin(account);
//            return new ResponseEntity<>(jwt,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("/duplicate-email/{data}")
//    public ResponseEntity<?> checkDuplicateEmail(@PathVariable("data") String email){
//        try{
//            Account account = iAccountService.findAccountByUEmail(email).get();
//            if (account != null){
//                    return new ResponseEntity<>("Exist", HttpStatus.OK);
//                }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Not Exist",HttpStatus.OK);
//    }
//
//    @GetMapping("/duplicate-username/{data}")
//    public ResponseEntity<?> checkDuplicateUsername(@PathVariable("data") String username){
//        try{
//            Account account = iAccountService.findAccountByUsername(username).get();
//            if (account != null){
//                return new ResponseEntity<>("Exist", HttpStatus.OK);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Not Exist",HttpStatus.OK);
//    }
//
//}
