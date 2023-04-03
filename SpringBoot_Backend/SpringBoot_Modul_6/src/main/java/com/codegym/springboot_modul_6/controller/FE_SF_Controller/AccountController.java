package com.codegym.springboot_modul_6.Controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.dto.AccountDto;
import com.codegym.springboot_modul_6.Service.FE_SF_Service.IAccountService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/account")
public class AccountController {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private RequestMapper requestMapper;

    @PostMapping("/signup")
    public ResponseEntity<?> addAccount(@RequestBody AccountDto accountDto){
        Account account = new Account();
        account = requestMapper.toAccount(accountDto);
        iAccountService.save(account);
        return new ResponseEntity<>("Add successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public  ResponseEntity<?> login(@RequestBody AccountDto accountDto){
        try{
            Account account = iAccountService.findAccountByUsername(accountDto.getUsername()).get();
            if (account != null) {
                if (account.getPassword().equals(accountDto.getPassword())) {
                    return new ResponseEntity<>("Login successfully", HttpStatus.OK);
                }
            }
        }catch (Exception e){
        e.printStackTrace();
        }
        return new ResponseEntity<>("Login fail",HttpStatus.OK);
    }

    @GetMapping("/duplicate-email/{data}")
    public ResponseEntity<?> checkDuplicateEmail(@PathVariable String email){
        try{
            Account account = iAccountService.findAccountByUEmail(email).get();
            if (account != null){
                    return new ResponseEntity<>("Not Exist", HttpStatus.OK);
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Exist",HttpStatus.OK);
    }
}
