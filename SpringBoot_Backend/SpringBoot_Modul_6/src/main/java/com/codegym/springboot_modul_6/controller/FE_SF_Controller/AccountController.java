package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.AccountDto;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/account")
public class AccountController {
    @Autowired
    private ThirdService thirdService;

    @PostMapping("/signup")
    public ResponseEntity<?> addAccount(@RequestBody AccountDto accountDto){
        try {
            Account account = thirdService.signUp(accountDto);
            if (account !=null){
                return new ResponseEntity<>("Add successfully", HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Fail", HttpStatus.OK);
    }

    @PostMapping("/login")
    public  ResponseEntity<?> login(@RequestBody AccountDto accountDto){
        try {
            String token = thirdService.login(accountDto);
            if (token != null){
                return new ResponseEntity<>(token,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/duplicate-email/{data}")
    public ResponseEntity<?> checkDuplicateEmail(@PathVariable("data") String email){
        try{
            Account account = thirdService.checkValidateEmail(email);
            if (account != null){
                return new ResponseEntity<>("Exist",HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Exist",HttpStatus.OK);
    }

    @GetMapping("/duplicate-username/{data}")
    public ResponseEntity<?> checkDuplicateUsername(@PathVariable("data") String username){
        try{
            Account account = thirdService.checkValidateUsernmae(username);
            if (account != null){
                return new ResponseEntity<>("Exist", HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Exist",HttpStatus.OK);
    }

}
