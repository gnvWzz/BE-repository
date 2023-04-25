package com.codegym.springboot_modul_6.controller.fe_sf_controller;


import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Account;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.AccountDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.AccountModel;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping(value = "/api/account")
public class AccountController {
    @Autowired
    private ThirdService thirdService;

    @PostMapping("/signup")
    public ResponseEntity<?> addAccount(@RequestBody AccountDto accountDto) {
        try {
            Account account = thirdService.signUp(accountDto);
            if (account != null) {
                return new ResponseEntity<>("Add successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Fail", HttpStatus.NO_CONTENT);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDto accountDto) {
        try {
            String token = thirdService.login(accountDto);
            if (token != null) {
                AccountModel accountModel = new AccountModel();
                accountModel.setToken(token);
                accountModel.setUsername(accountDto.getUsername());
                return new ResponseEntity<>(accountModel, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login-owner")
    public ResponseEntity<?> loginOwner(@RequestBody AccountDto accountDto) {
        try {
            String token = thirdService.loginOwner(accountDto);
            if (token != null) {
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/duplicate-email/{data}")
    public ResponseEntity<?> checkDuplicateEmail(@PathVariable("data") String email) {
        try {
            Account account = thirdService.checkValidateEmail(email);
            if (account != null) {
                return new ResponseEntity<>("Exist", HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Exist", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/duplicate-username/{data}")
    public ResponseEntity<?> checkDuplicateUsername(@PathVariable("data") String username) {
        try {
            Account account = thirdService.checkValidateUsernmae(username);

            if (account != null) {
                return new ResponseEntity<>("Exist", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Exist", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/duplicate-phone/{data}")
    public ResponseEntity<?> checkDuplicatePhone(@PathVariable("data") String phone) {
        try {
            Account account = thirdService.checkValidatePhone(phone);

            if (account != null) {
                return new ResponseEntity<>("Exist", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Exist", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/signup-owner")
    public ResponseEntity<?> addAccountOwner(@RequestBody AccountDto accountDto) {
        try {
            Account account = thirdService.signUpOwner(accountDto);
            if (account != null) {
                return new ResponseEntity<>("Add successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Fail", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username){
        try {
            AccountDto accountDto = thirdService.getUser(username);
            if (accountDto != null) {
                return new ResponseEntity<>(accountDto, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PutMapping("/")
    public ResponseEntity<?>update(@RequestBody AccountDto accountDto){
        try {
            Account account = thirdService.update(accountDto);

            if (account != null) {
                return new ResponseEntity<>( HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @GetMapping("/password/{data}/{usernameLogin}")
    public ResponseEntity<?> checkPassword(@PathVariable("data") String password,
                                                    @PathVariable ("usernameLogin") String username) {
        try {
            boolean checkPassword = thirdService.checkPassword(password,username);
            if (checkPassword) {
                return new ResponseEntity<>("Exist", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Exist", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/password/{data}/{usernameLogin}")
    public ResponseEntity<?> updatePassword(@PathVariable("data") String password,
                                                    @PathVariable ("usernameLogin") String username){
        try {
            Account account = thirdService.checkValidateUsernmae(username);
            if (account != null) {
                thirdService.updatePassword(account,password);
                return new ResponseEntity<>( HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }


}
