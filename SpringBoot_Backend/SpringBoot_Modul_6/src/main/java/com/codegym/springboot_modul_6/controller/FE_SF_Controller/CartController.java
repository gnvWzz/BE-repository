package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.CartSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.CartDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.CartDto;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ICartService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.LongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/cart")
public class CartController {

    @Autowired
    private LongMapper mapper;

    @Autowired
    private ICartService iCartService;

    @PostMapping(value = "add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartDto carDto){
        try {
            CartSF cartSF =  mapper.mapperCart(carDto);
            if (cartSF != null) {
                iCartService.save(cartSF);
                return new ResponseEntity<>("Add successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Fail", HttpStatus.OK);
    }
}
